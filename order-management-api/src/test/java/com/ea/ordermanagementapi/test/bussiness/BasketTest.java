package com.ea.ordermanagementapi.test.bussiness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.util.Assert.notEmpty;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ea.ordermanagementapi.domain.Basket;
import com.ea.ordermanagementapi.test.AbstractAPITest;
import com.ea.ordermanagementapi.test.AddToBasketResponse;

public class BasketTest extends AbstractAPITest
{
    @Test
    public void add_to_basket_test()
    {
        // -----

        AddToBasketResponse resp = addToBasket();

        // -----

        List<Basket> customerBasket = basketAPI.getCustomerBasket(resp.getCustomerId());
        notEmpty(customerBasket);
        assertEquals(Long.parseLong("20"), Long.parseLong(customerBasket.get(0).getQuantity().toString()));
        assertEquals(resp.getCustomerId(), customerBasket.get(0).getCustomerId());
        assertEquals(resp.getProductId(), customerBasket.get(0).getProductId());
    }
}
