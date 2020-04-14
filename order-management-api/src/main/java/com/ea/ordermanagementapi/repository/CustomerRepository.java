package com.ea.ordermanagementapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ea.ordermanagementapi.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>
{
    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}