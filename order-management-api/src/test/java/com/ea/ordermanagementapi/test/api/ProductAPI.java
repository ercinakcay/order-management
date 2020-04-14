package com.ea.ordermanagementapi.test.api;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ea.ordermanagementapi.domain.Product;
import com.ea.ordermanagementapi.test.AbstractRestService;
import com.ea.ordermanagementapi.util.rest.Response;

@Component
public class ProductAPI extends AbstractRestService
{
    public List<Product> listAllActiveProducts()
    {
        Response response = get("/product/list/all/ACTIVE");
        return toList(response.getData(), Product.class);
    }
}
