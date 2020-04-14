package com.ea.ordermanagementapi.util.rest;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError
{
    private String name;

    private String message;

    private String trace;

    public ResponseError()
    {
    }

    public ResponseError(Throwable exception)
    {
        this.name = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.trace = ExceptionUtils.getStackTrace(exception);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getTrace()
    {
        return trace;
    }

    public void setTrace(String trace)
    {
        this.trace = trace;
    }

    @Override
    public String toString()
    {
        return "ResponseError{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", trace='" + trace + '\'' +
                '}';
    }
}
