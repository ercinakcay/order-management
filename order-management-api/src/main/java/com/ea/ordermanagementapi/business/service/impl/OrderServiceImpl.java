package com.ea.ordermanagementapi.business.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ea.ordermanagementapi.business.request.MakeOrderRequest;
import com.ea.ordermanagementapi.business.service.BasketService;
import com.ea.ordermanagementapi.business.service.CustomerService;
import com.ea.ordermanagementapi.business.service.OrderService;
import com.ea.ordermanagementapi.business.service.PaymentService;
import com.ea.ordermanagementapi.business.service.ProductService;
import com.ea.ordermanagementapi.constant.CacheConstants;
import com.ea.ordermanagementapi.domain.Basket;
import com.ea.ordermanagementapi.domain.Customer;
import com.ea.ordermanagementapi.domain.Order;
import com.ea.ordermanagementapi.exception.InvalidDataException;
import com.ea.ordermanagementapi.exception.InvalidInputException;
import com.ea.ordermanagementapi.repository.OrderRepository;
import com.ea.ordermanagementapi.util.DateUtils;

@Service
public class OrderServiceImpl extends AbstractServiceImpl implements OrderService
{
    private OrderRepository orderRepository;

    private CustomerService customerService;

    private BasketService basketService;

    private ProductService productService;

    private PaymentService paymentService;

    private TaskExecutor taskExecutor;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerService customerService,
                            BasketService basketService,
                            ProductService productService,
                            PaymentService paymentService,
                            TaskExecutor taskExecutor)
    {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.basketService = basketService;
        this.productService = productService;
        this.paymentService = paymentService;
        this.taskExecutor = taskExecutor;
    }

    @Override
    @Cacheable(value = CacheConstants.Order.CUSTOMER_ORDER_LIST)
    public List<Order> listOrdersByCustomerId(String customerId)
    {
        if (StringUtils.isEmpty(customerId))
        {
            throw new InvalidInputException(getMessage("invalid.input.customer.id"));
        }
        return orderRepository.findAllByCustomerId(customerId);
    }

    @Override
    @Transactional
    public Order makeOrder(MakeOrderRequest request)
    {
        // -----

        validateOrderRequest(request);

        // -----

        return saveOrder(request);
    }

    private void validateOrderRequest(MakeOrderRequest request)
    {
        validateOrderDate(request);
        validateCustomer(request);
        validateBasketData(request);

        List<Basket> basketList = getBasketList(request);
        validateOrderBasket(basketList);
        validateProductQuantity(basketList);
    }

    private List<Basket> getBasketList(MakeOrderRequest request)
    {
        return request.getBasketIds().stream()
                .map(id -> basketService.getBasketById(id)).collect(Collectors.toList());
    }

    private void validateBasketData(MakeOrderRequest request)
    {
        List<String> basketIds = request.getBasketIds();
        if (null == basketIds || CollectionUtils.isEmpty(basketIds))
        {
            throw new InvalidDataException(getMessage("invalid.data.order.basket"));
        }
    }

    private void validateProductQuantity(List<Basket> basketList)
    {
        basketList.forEach(basket -> {
            if (!productService.isProductQuantityAvailable(basket.getProductId(), basket.getQuantity()))
                throw new InvalidDataException(getMessage("invalid.data.order.product.quantity"));
        });
    }

    private void validateCustomer(MakeOrderRequest request)
    {
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if (null == customer)
        {
            throw new InvalidDataException(getMessage("invalid.data.customer.non.exist"));
        }
    }

    private void validateOrderBasket(List<Basket> basketList)
    {
        basketList.forEach(basket -> {
            if (null == basket)
                throw new InvalidDataException(getMessage("invalid.data.order.basket"));
            if (basket.getQuantity() == 0)
                throw new InvalidDataException(getMessage("invalid.data.basket.quantity"));
        });
    }

    private void validateOrderDate(MakeOrderRequest request)
    {
        if (StringUtils.isEmpty(request.getOrderDate()) || !DateUtils.isAfterNow(request.getOrderDate()))
        {
            throw new InvalidInputException(getMessage("invalid.input.order.date"));
        }
    }

    private Order saveOrder(MakeOrderRequest request)
    {
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setBasketIds(request.getBasketIds());
        order.setOrderDate(DateUtils.asEpocSecond(request.getOrderDate()));
        Order savedOrder = orderRepository.save(order);

        // -----

        doPayment(savedOrder);

        // -----

        return savedOrder;
    }


    private void doPayment(Order savedOrder)
    {
        boolean isPaymentSuccessful = paymentService.doPayment(savedOrder);
        if (isPaymentSuccessful)
        {
            updateProductCurrentQuantity(savedOrder);
        }
    }

    private void updateProductCurrentQuantity(Order savedOrder)
    {
        List<Basket> basketList = savedOrder.getBasketIds().stream()
                .map(id -> basketService.getBasketById(id)).collect(Collectors.toList());

        for (Basket basket : basketList)
        {
            taskExecutor.execute(new Runnable()
            {
                public void run()
                {
                    productService.updateProductQuantity(basket.getProductId(), basket.getQuantity());
                }
            });
        }
    }
}
