package com.ea.ordermanagementapi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ea.ordermanagementapi.aspect.TransactionAwareIdentifier;
import com.ea.ordermanagementapi.util.DateUtils;

@Document(collection = "Order")
public class Order implements Serializable, TransactionAwareIdentifier
{
    private static final long serialVersionUID = 377152485742600531L;

    @Id
    public String id;

    private String customerId;

    private List<String> basketIds = new ArrayList<>();

    private Long orderDate;

    private String createdDate = DateUtils.getNowDate();

    public Order()
    {
    }

    public String getId()
    {
        return id;
    }

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

    public Long getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Long orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(String createdDate)
    {
        this.createdDate = createdDate;
    }

    @Override
    public String getTransactionAwareIdentifier()
    {
        // TODO: add basket counts too.
        return "customerId=" + this.customerId + "-"
                + "basketIds=" + String.join("-", this.basketIds);
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", basketIds=" + basketIds +
                ", orderDate=" + orderDate +
                ", createdDate=" + createdDate +
                '}';
    }
}
