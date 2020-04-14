package com.ea.ordermanagementapi.business.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.ordermanagementapi.business.request.AddBasketRequest;
import com.ea.ordermanagementapi.business.service.BasketService;
import com.ea.ordermanagementapi.business.service.CustomerService;
import com.ea.ordermanagementapi.business.service.ProductService;
import com.ea.ordermanagementapi.constant.CacheConstants;
import com.ea.ordermanagementapi.domain.Basket;
import com.ea.ordermanagementapi.domain.Customer;
import com.ea.ordermanagementapi.domain.Product;
import com.ea.ordermanagementapi.exception.InvalidDataException;
import com.ea.ordermanagementapi.exception.InvalidInputException;
import com.ea.ordermanagementapi.repository.BasketRepository;

@Service
public class BasketServiceImpl extends AbstractServiceImpl implements BasketService
{
    private BasketRepository basketRepository;

    private CustomerService customerService;

    private ProductService productService;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository,
                             CustomerService customerService,
                             ProductService productService)
    {
        this.basketRepository = basketRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    @Cacheable(value = CacheConstants.Basket.FIND_BASKET_BY_ID, unless="#result == null")
    public Basket getBasketById(String basketId)
    {
        if (StringUtils.isEmpty(basketId))
        {
            throw new InvalidInputException(getMessage("invalid.input.basket.id"));
        }

        Optional<Basket> byId = basketRepository.findById(basketId);
        return byId.orElse(null);
    }

    @Override
    public List<Basket> getCustomerBasket(String customerId)
    {
        if (StringUtils.isEmpty(customerId))
        {
            throw new InvalidInputException(getMessage("invalid.input.customer.id"));
        }

        return basketRepository.findAllByCustomerId(customerId);
    }

    @Override
    @Transactional
    public Basket addToBasket(AddBasketRequest request)
    {
        // -----

        validateBasketRequest(request);

        // -----

        return saveBasket(request);
    }

    private void validateBasketRequest(AddBasketRequest request)
    {
        validateCustomer(request);
        validateProductQuantity(request);
    }

    private void validateProductQuantity(AddBasketRequest request)
    {
        if (!productService.isProductQuantityAvailable(request.getProductId(), request.getQuantity()))
        {
            throw new InvalidDataException(getMessage("invalid.data.order.product.quantity"));
        }
    }

    private void validateCustomer(AddBasketRequest request)
    {
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if (null == customer)
        {
            throw new InvalidDataException(getMessage("invalid.data.customer.non.exist"));
        }
    }

    private Basket saveBasket(AddBasketRequest request)
    {
        Basket entity = new Basket();
        entity.setCustomerId(request.getCustomerId());
        entity.setProductId(request.getProductId());
        entity.setQuantity(request.getQuantity());
        entity.setPrice(calculateTotalPrice(request));
        return basketRepository.save(entity);
    }

    private BigDecimal calculateTotalPrice(AddBasketRequest request)
    {
        Product product = productService.getProductById(request.getProductId());
        return product.getPrice().multiply(new BigDecimal(request.getQuantity()));
    }
}
