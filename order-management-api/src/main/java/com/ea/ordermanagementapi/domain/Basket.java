package com.ea.ordermanagementapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ea.ordermanagementapi.aspect.TransactionAwareIdentifier;
import com.ea.ordermanagementapi.util.DateUtils;

@Document(collection = "Basket")
public class Basket implements Serializable, TransactionAwareIdentifier
{
    private static final long serialVersionUID = 1558349982636284147L;

    @Id
    public String id;

    private String customerId;

    private String productId;

    public Integer quantity;

    public BigDecimal price;

    private String createdDate = DateUtils.getNowDate();

    public Basket()
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

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
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
        return this.productId + "-" + quantity;
    }
}
