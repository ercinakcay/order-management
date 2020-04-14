package com.ea.ordermanagementapi.test.bussiness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.util.Assert.notEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ea.ordermanagementapi.business.request.MakeOrderRequest;
import com.ea.ordermanagementapi.domain.Basket;
import com.ea.ordermanagementapi.domain.Order;
import com.ea.ordermanagementapi.domain.Product;
import com.ea.ordermanagementapi.test.AbstractAPITest;
import com.ea.ordermanagementapi.test.AddToBasketResponse;
import com.ea.ordermanagementapi.test.api.OrderAPI;

public class OrderTest extends AbstractAPITest
{
    private static final Logger logger = LoggerFactory.getLogger(OrderTest.class);

    @Autowired
    private OrderAPI orderAPI;

    @Test
    public void make_order_test()
    {
        // -----

        AddToBasketResponse resp = addToBasket();

        // -----

        String quantity = "20";
        List<Basket> customerBasket = basketAPI.getCustomerBasket(resp.getCustomerId());
        notEmpty(customerBasket);
        assertEquals(Long.parseLong(quantity), Long.parseLong(customerBasket.get(0).getQuantity().toString()));
        assertEquals(resp.getCustomerId(), customerBasket.get(0).getCustomerId());
        assertEquals(resp.getProductId(), customerBasket.get(0).getProductId());

        // -----

        MakeOrderRequest request = new MakeOrderRequest();
        request.setBasketIds(customerBasket.stream().map(Basket::getId).collect(Collectors.toList()));
        request.setCustomerId(resp.getCustomerId());
        request.setOrderDate("202104101010");
        Order order = orderAPI.makeOrder(request);

        logger.info("Order is done with this input: {}", order);

        // -----

        List<Order> customerOrders = orderAPI.listCustomerOrders(resp.getCustomerId());
        assertEquals(resp.getCustomerId(), customerOrders.get(0).getCustomerId());

        assertNotNull(customerOrders.get(0).getTransactionAwareIdentifier());


        // ----
        List<Product> products = productAPI.listAllActiveProducts();
        logger.info("For product id: {} , product total quantity: {}, ordered quantity: {}, rest quantity: {}",
                resp.getProductId(), resp.getProductTotalQuantity(), Integer.parseInt(quantity),
                resp.getProductTotalQuantity() - Integer.parseInt(quantity));
        assertEquals((int) products.get(0).getQuantity(), resp.getProductTotalQuantity() - Integer.parseInt(quantity));
    }
}
