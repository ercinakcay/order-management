package com.ea.ordermanagementapi.business.service;

import java.util.List;

import com.ea.ordermanagementapi.business.request.AddBasketRequest;
import com.ea.ordermanagementapi.domain.Basket;

public interface BasketService
{
    Basket getBasketById(String basketId);

    List<Basket> getCustomerBasket(String customerId);

    Basket addToBasket(AddBasketRequest request);
}
