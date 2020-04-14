package com.ea.ordermanagementapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ea.ordermanagementapi.domain.enums.ProductStatus;
import com.ea.ordermanagementapi.domain.enums.QuantityType;
import com.ea.ordermanagementapi.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Product")
public class Product implements Serializable
{
    private static final long serialVersionUID = -2602756563467953835L;

    @Id
    public String id;

    public String name;

    public String description;

    public BigDecimal price;

    public Integer quantity;

    public QuantityType quantityType;

    public ProductStatus productStatus;

    private String createdDate = DateUtils.getNowDate();

    public Product()
    {
    }

    public Product(String name, String description, BigDecimal price, Integer quantity, QuantityType quantityType,
                   ProductStatus productStatus)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.productStatus = productStatus;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    @JsonIgnore
    public boolean isQuantityOverflows(Integer orderedQuantity)
    {
        return this.quantity - orderedQuantity <= 0;
    }

    public ProductStatus getProductStatus()
    {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus)
    {
        this.productStatus = productStatus;
    }

    public QuantityType getQuantityType()
    {
        return quantityType;
    }

    public void setQuantityType(QuantityType quantityType)
    {
        this.quantityType = quantityType;
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
    public String toString()
    {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", quantityType=" + quantityType +
                ", productStatus=" + productStatus +
                ", createdDate=" + createdDate +
                '}';
    }
}
