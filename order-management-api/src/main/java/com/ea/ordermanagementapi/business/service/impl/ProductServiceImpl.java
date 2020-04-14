package com.ea.ordermanagementapi.business.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.ordermanagementapi.business.service.ProductService;
import com.ea.ordermanagementapi.constant.CacheConstants;
import com.ea.ordermanagementapi.domain.Product;
import com.ea.ordermanagementapi.domain.enums.ProductStatus;
import com.ea.ordermanagementapi.exception.InvalidDataException;
import com.ea.ordermanagementapi.repository.ProductRepository;

@Service
public class ProductServiceImpl extends AbstractServiceImpl implements ProductService
{
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    @Cacheable(value = CacheConstants.Product.LIST_PRODUCTS_BY_STATUS)
    public List<Product> listProductsByStatus(ProductStatus status)
    {
        return productRepository.findAllByProductStatus(status);
    }

    @Override
    public boolean isProductQuantityAvailable(String productId, Integer quantity)
    {
        Product product = productRepository.findById(productId).orElse(null);
        if (null != product)
        {
            return !product.isQuantityOverflows(quantity);
        }
        return false;
    }

    @Override
    @Transactional
    @CacheEvict(value = {
                CacheConstants.Product.LIST_PRODUCTS_BY_STATUS,
                CacheConstants.Product.FIND_ONE
            },
            allEntries=true)
    public Product updateProductQuantity(String productId, Integer quantity)
    {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product updatedProduct = optionalProduct.orElse(null);

        if (null == updatedProduct)
        {
            throw new InvalidDataException(getMessage("invalid.data.product.non.exist"));
        }
        updatedProduct.setQuantity(updatedProduct.getQuantity() - quantity);
        Product response;

        // Operation will be locked thread while updating product data.
        // This may cause connection limit and performance issues.
        synchronized(productId)
        {
            logger.info("Thread id: {} updating product id: {}", Thread.currentThread().getId(), productId);
            response = productRepository.save(updatedProduct);
        }
        return response;
    }

    @Override
    @Cacheable(value = CacheConstants.Product.FIND_ONE)
    public Product getProductById(String productId)
    {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
    }
}
