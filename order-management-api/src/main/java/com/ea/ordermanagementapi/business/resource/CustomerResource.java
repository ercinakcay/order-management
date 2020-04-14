package com.ea.ordermanagementapi.business.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ea.ordermanagementapi.business.service.CustomerService;
import com.ea.ordermanagementapi.domain.Customer;
import com.ea.ordermanagementapi.util.rest.Resource;
import com.ea.ordermanagementapi.util.rest.Response;

@Resource("/customer")
public class CustomerResource
{
    private CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response addCustomer(@RequestBody Customer customer)
    {
        return new Response(customerService.addCustomer(customer));
    }

    @GetMapping(value = "/getOne")
    public Response findOneCustomer()
    {
        return new Response(customerService.findOneCustomer());
    }

    @GetMapping(value = "/{customerId}")
    public Response getCustomerById(@PathVariable String customerId)
    {
        return new Response(customerService.getCustomerById(customerId));
    }
}
