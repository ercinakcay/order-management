package com.ea.ordermanagementapi.util.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Response
{
    private Object data;
    private boolean valid = true;
    private ResponseError error = null;

    public Response()
    {
    }

    public Response(Object data)
    {
        this.data = data;
    }

    public Response(Throwable exception)
    {
        setError(exception);
    }

    private void setError(Throwable exception)
    {
        setInvalid();
        this.error = new ResponseError(exception);
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public boolean isValid()
    {
        return valid;
    }

    public void setInvalid()
    {
        this.valid = false;
    }
}
