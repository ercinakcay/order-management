package com.ea.ordermanagementapi.business.service;

import java.util.List;

import com.ea.ordermanagementapi.business.request.MakeOrderRequest;
import com.ea.ordermanagementapi.domain.Order;

public interface OrderService
{
    List<Order> listOrdersByCustomerId(String customerId);

    Order makeOrder(MakeOrderRequest request);
}
