package com.ea.ordermanagementapi.test.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ea.ordermanagementapi.business.request.AddBasketRequest;
import com.ea.ordermanagementapi.domain.Basket;
import com.ea.ordermanagementapi.test.AbstractRestService;
import com.ea.ordermanagementapi.util.rest.Response;

@Component
public class BasketAPI extends AbstractRestService
{
    public void addToBasket(AddBasketRequest request)
    {
        post(Basket.class, "/basket/customer/addBasket", request);
    }

    public List<Basket> getCustomerBasket(String customerId)
    {
        Response response = get("/basket/customer/" + customerId);
        return toList(response.getData(), Basket.class);
    }
}
