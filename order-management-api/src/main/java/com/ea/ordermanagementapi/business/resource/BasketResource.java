package com.ea.ordermanagementapi.business.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ea.ordermanagementapi.business.request.AddBasketRequest;
import com.ea.ordermanagementapi.business.service.BasketService;
import com.ea.ordermanagementapi.util.rest.Resource;
import com.ea.ordermanagementapi.util.rest.Response;

@Resource("/basket")
public class BasketResource
{
    private BasketService basketService;

    @Autowired
    public BasketResource(BasketService basketService)
    {
        this.basketService = basketService;
    }

    @GetMapping(value = "/{basketId}")
    public Response getBasketById(@PathVariable String basketId)
    {
        return new Response(basketService.getBasketById(basketId));
    }

    @GetMapping(value = "/customer/{customerId}")
    public Response getCustomerBasket(@PathVariable String customerId)
    {
        return new Response(basketService.getCustomerBasket(customerId));
    }

    @PostMapping(value = "/customer/addBasket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response addToBasket(@RequestBody AddBasketRequest request)
    {
        return new Response(basketService.addToBasket(request));
    }
}
