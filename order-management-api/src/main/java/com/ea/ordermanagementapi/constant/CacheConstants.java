package com.ea.ordermanagementapi.constant;

public interface CacheConstants
{
    interface Customer
    {
        String FIND_ONE = "FIND_ONE_CUSTOMER";
        String FIND_CUSTOMER_BY_ID = "FIND_CUSTOMER_BY_ID";
    }

    interface Basket
    {
        String FIND_BASKET_BY_ID = "FIND_BASKET_BY_ID";
    }

    interface Product
    {
        String FIND_ONE = "FIND_ONE_PRODUCT";
        String LIST_PRODUCTS_BY_STATUS = "LIST_PRODUCTS_BY_STATUS";
    }

    interface Order
    {
        String CUSTOMER_ORDER_LIST = "CUSTOMER_ORDER_LIST";
    }

}
