package com.ea.ordermanagementapi.business.request;

import java.io.Serializable;
import java.util.List;

public class MakeOrderRequest implements Serializable
{
    private static final long serialVersionUID = -6950998348310489666L;

    private String customerId;

    private List<String> basketIds;

    // Date should be enter as 'yyyyMMddHHmm' pattern.
    private String orderDate;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public List<String> getBasketIds()
    {
        return basketIds;
    }

    public void setBasketIds(List<String> basketIds)
    {
        this.basketIds = basketIds;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    @Override
    public String toString()
    {
        return "MakeOrderRequest{" +
                "customerId='" + customerId + '\'' +
                ", basketIds=" + basketIds +
                '}';
    }
}
