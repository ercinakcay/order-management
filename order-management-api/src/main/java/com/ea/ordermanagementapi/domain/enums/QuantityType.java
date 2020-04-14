package com.ea.ordermanagementapi.domain.enums;

public enum QuantityType
{
    ITEM("adet"),
    KG("kg");

    private String name;

    public String getName()
    {
        return name;
    }

    QuantityType(String name)
    {
        this.name = name;
    }
}
