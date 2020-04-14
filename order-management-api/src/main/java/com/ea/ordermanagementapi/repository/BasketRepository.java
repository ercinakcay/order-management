package com.ea.ordermanagementapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ea.ordermanagementapi.domain.Basket;

public interface BasketRepository extends MongoRepository<Basket, String>
{
    List<Basket> findAllByCustomerId(String customerId);
}
