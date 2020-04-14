package com.ea.ordermanagementapi.exception;

public class InvalidDataException extends APIException
{
    public InvalidDataException(String msg)
    {
        super(msg);
    }

    public InvalidDataException(String msg, Throwable throwable)
    {
        super(msg, throwable);
    }
}
