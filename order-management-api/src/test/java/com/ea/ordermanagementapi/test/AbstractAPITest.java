package com.ea.ordermanagementapi.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.ea.ordermanagementapi.business.request.AddBasketRequest;
import com.ea.ordermanagementapi.domain.Customer;
import com.ea.ordermanagementapi.domain.Product;
import com.ea.ordermanagementapi.test.api.BasketAPI;
import com.ea.ordermanagementapi.test.api.CustomerAPI;
import com.ea.ordermanagementapi.test.api.ProductAPI;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestPropertySource(locations = "classpath:test-env-application.properties")
public class AbstractAPITest
{
    private static final Logger logger = LoggerFactory.getLogger(AbstractAPITest.class);

    @Autowired
    public CustomerAPI customerAPI;

    @Autowired
    public ProductAPI productAPI;

    @Autowired
    public BasketAPI basketAPI;

    public AddToBasketResponse addToBasket()
    {
        Customer oneCustomer = customerAPI.getOneCustomer();
        logger.info("Customer: {}", oneCustomer);
        assertNotNull(oneCustomer, "Customer data is null");

        List<Product> products = productAPI.listAllActiveProducts();
        logger.info("Product List: {}", products);

        // -----

        String customerId = oneCustomer.getId();
        String productId = products.get(0).getId();
        AddBasketRequest request = new AddBasketRequest();
        request.setCustomerId(customerId);
        request.setProductId(productId);
        request.setQuantity(20);
        basketAPI.addToBasket(request);

        AddToBasketResponse resp = new AddToBasketResponse();
        resp.setCustomerId(customerId);
        resp.setProductId(productId);
        resp.setProductTotalQuantity(products.get(0).getQuantity());
        return resp;
    }
}


