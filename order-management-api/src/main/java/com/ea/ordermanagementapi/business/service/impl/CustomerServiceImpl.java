package com.ea.ordermanagementapi.business.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ea.ordermanagementapi.business.service.CustomerService;
import com.ea.ordermanagementapi.constant.CacheConstants;
import com.ea.ordermanagementapi.domain.Customer;
import com.ea.ordermanagementapi.exception.InvalidInputException;
import com.ea.ordermanagementapi.repository.CustomerRepository;

@Service
public class CustomerServiceImpl extends AbstractServiceImpl implements CustomerService
{
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Override
    @CacheEvict(value = CacheConstants.Customer.FIND_CUSTOMER_BY_ID, key="#result.id")
    public Customer addCustomer(Customer customer)
    {
        if (StringUtils.isEmpty(customer.getFirstName())
                || StringUtils.isEmpty(customer.getLastName()))
        {
            throw new InvalidInputException(getMessage("invalid.input.customer"));
        }
        return customerRepository.save(customer);
    }

    @Override
    @Cacheable(value = CacheConstants.Customer.FIND_ONE, unless="#result == null")
    public Customer findOneCustomer()
    {
        return customerRepository.findByFirstName("Default");
    }

    @Override
    public Customer getCustomerById(String customerId)
    {
        if (StringUtils.isEmpty(customerId))
        {
            throw new InvalidInputException(getMessage("invalid.input.customer.id"));
        }

        Optional<Customer> byId = customerRepository.findById(customerId);
        return byId.orElse(null);
    }
}
