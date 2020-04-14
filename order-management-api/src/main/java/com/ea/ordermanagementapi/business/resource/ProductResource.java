package com.ea.ordermanagementapi.business.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ea.ordermanagementapi.business.service.ProductService;
import com.ea.ordermanagementapi.domain.enums.ProductStatus;
import com.ea.ordermanagementapi.util.rest.Resource;
import com.ea.ordermanagementapi.util.rest.Response;

@Resource("/product")
public class ProductResource
{
    private ProductService productService;

    @Autowired
    public ProductResource(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping(value = "/list/all/{status}")
    public Response listActiveProducts(@PathVariable ProductStatus status)
    {
        return new Response(productService.listProductsByStatus(status));
    }
}
