package com.ea.ordermanagementapi.exception;

public class APIException extends RuntimeException
{
    public APIException(final String msg)
    {
        super(msg);
    }

    public APIException(final String msg, final Throwable throwable) {
        super(msg, throwable);
    }
}
