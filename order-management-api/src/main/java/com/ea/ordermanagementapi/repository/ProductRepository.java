package com.ea.ordermanagementapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ea.ordermanagementapi.domain.Product;
import com.ea.ordermanagementapi.domain.enums.ProductStatus;

public interface ProductRepository extends MongoRepository<Product, String>
{
    List<Product> findAllByProductStatus(ProductStatus productStatus);

    Product findByName(String name);

}
