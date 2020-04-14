package com.ea.ordermanagementapi.business.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ea.ordermanagementapi.business.request.MakeOrderRequest;
import com.ea.ordermanagementapi.business.service.OrderService;
import com.ea.ordermanagementapi.util.rest.Resource;
import com.ea.ordermanagementapi.util.rest.Response;

@Resource("/order")
public class OrderResource
{
    private OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping(value = "/customer/{customerId}/listAll")
    public Response listActiveProducts(@PathVariable String customerId)
    {
        return new Response(orderService.listOrdersByCustomerId(customerId));
    }

    @PostMapping(value = "/customer/makeNew", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response makeOrder(@RequestBody MakeOrderRequest makeOrderRequest)
    {
        return new Response(orderService.makeOrder(makeOrderRequest));
    }
}
