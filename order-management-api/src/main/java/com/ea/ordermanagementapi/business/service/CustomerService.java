package com.ea.ordermanagementapi.business.service;

import com.ea.ordermanagementapi.domain.Customer;

public interface CustomerService
{
    Customer findOneCustomer();

    Customer getCustomerById(String customerId);

    Customer addCustomer(Customer customer);
}
