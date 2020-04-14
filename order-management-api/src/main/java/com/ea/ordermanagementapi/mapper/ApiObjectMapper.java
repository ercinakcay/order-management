package com.ea.ordermanagementapi.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ApiObjectMapper extends ObjectMapper
{
    public ApiObjectMapper()
    {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }
}

