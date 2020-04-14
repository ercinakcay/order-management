package com.ea.ordermanagementapi.business.request;

import java.io.Serializable;

public class AddBasketRequest implements Serializable
{
    private static final long serialVersionUID = 4165980420891384353L;

    private String customerId;

    private String productId;

    public Integer quantity;

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

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "AddBasketRequest{" +
                "customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
