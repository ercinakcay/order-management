package com.ea.ordermanagementapi.test.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ea.ordermanagementapi.business.request.MakeOrderRequest;
import com.ea.ordermanagementapi.domain.Order;
import com.ea.ordermanagementapi.test.AbstractRestService;
import com.ea.ordermanagementapi.util.rest.Response;

@Component
public class OrderAPI extends AbstractRestService
{
    public Order makeOrder(MakeOrderRequest request)
    {
        return post(Order.class, "/order/customer/makeNew", request);
    }

    public List<Order> listCustomerOrders(String customerId)
    {
        Response response = get("/order/customer/" + customerId + "/listAll");
        return toList(response.getData(), Order.class);
    }
}
