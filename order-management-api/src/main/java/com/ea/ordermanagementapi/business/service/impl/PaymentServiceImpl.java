package com.ea.ordermanagementapi.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ea.ordermanagementapi.aspect.TransactionAware;
import com.ea.ordermanagementapi.business.service.PaymentService;
import com.ea.ordermanagementapi.domain.Order;

@Service
public class PaymentServiceImpl implements PaymentService
{
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    @TransactionAware
    public boolean doPayment(Order order)
    {
        logger.info("Payment operation of order is successfully done.");
        return true;
    }
}
