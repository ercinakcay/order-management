package com.ea.ordermanagementapi.exception;

public class TransactionAwareException extends APIException
{
    public TransactionAwareException(String msg)
    {
        super(msg);
    }

    public TransactionAwareException(String msg, Throwable throwable)
    {
        super(msg, throwable);
    }
}
