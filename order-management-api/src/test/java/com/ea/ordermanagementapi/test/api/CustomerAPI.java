package com.ea.ordermanagementapi.test.api;

import org.springframework.stereotype.Component;

import com.ea.ordermanagementapi.domain.Customer;
import com.ea.ordermanagementapi.test.AbstractRestService;

@Component
public class CustomerAPI extends AbstractRestService
{
    public Customer getOneCustomer()
    {
        return get(Customer.class, "/customer/getOne");
    }
}
