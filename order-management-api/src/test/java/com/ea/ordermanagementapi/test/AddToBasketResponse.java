package com.ea.ordermanagementapi.test;

public class AddToBasketResponse
{
    private String customerId;

    private String productId;

    private Integer productTotalQuantity;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public Integer getProductTotalQuantity()
    {
        return productTotalQuantity;
    }

    public void setProductTotalQuantity(Integer productTotalQuantity)
    {
        this.productTotalQuantity = productTotalQuantity;
    }
}
