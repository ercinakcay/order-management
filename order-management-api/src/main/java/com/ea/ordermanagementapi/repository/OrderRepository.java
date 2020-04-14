package com.ea.ordermanagementapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ea.ordermanagementapi.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String>
{
    List<Order> findAllByCustomerId(String customerId);

}
