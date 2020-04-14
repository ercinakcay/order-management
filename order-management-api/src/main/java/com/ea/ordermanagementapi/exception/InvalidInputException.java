package com.ea.ordermanagementapi.exception;

public class InvalidInputException extends APIException
{
    public InvalidInputException(String msg)
    {
        super(msg);
    }

    public InvalidInputException(String msg, Throwable throwable)
    {
        super(msg, throwable);
    }
}
