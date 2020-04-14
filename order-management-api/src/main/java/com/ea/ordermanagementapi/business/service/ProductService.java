package com.ea.ordermanagementapi.business.service;

import java.util.List;

import com.ea.ordermanagementapi.domain.Product;
import com.ea.ordermanagementapi.domain.enums.ProductStatus;

public interface ProductService
{
    List<Product> listProductsByStatus(ProductStatus status);

    boolean isProductQuantityAvailable(String productId, Integer quantity);

    Product updateProductQuantity(String productId, Integer quantity);

    Product getProductById(String productId);
}
