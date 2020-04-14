package com.ea.ordermanagementapi.business.service;

import com.ea.ordermanagementapi.domain.Order;

public interface PaymentService
{
    boolean doPayment(Order order);
}
