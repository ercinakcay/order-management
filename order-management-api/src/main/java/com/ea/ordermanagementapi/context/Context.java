package com.ea.ordermanagementapi.context;

import java.io.Serializable;

public class Context implements Serializable
{
    private static final long serialVersionUID = 3304408497314629477L;

    private String clientIP;
    private int clientPort;
    private String requestHost;
    private String requestURI;
    private String requestQuery;
    private String requestId;
    private String userAgent;

    public Context()
    {
    }

    public String getClientIP()
    {
        return clientIP;
    }

    public void setClientIP(String clientIP)
    {
        this.clientIP = clientIP;
    }

    public int getClientPort()
    {
        return clientPort;
    }

    public void setClientPort(int clientPort)
    {
        this.clientPort = clientPort;
    }

    public String getRequestHost()
    {
        return requestHost;
    }

    public void setRequestHost(String requestHost)
    {
        this.requestHost = requestHost;
    }

    public String getRequestURI()
    {
        return requestURI;
    }

    public void setRequestURI(String requestURI)
    {
        this.requestURI = requestURI;
    }

    public String getRequestQuery()
    {
        return requestQuery;
    }

    public void setRequestQuery(String requestQuery)
    {
        this.requestQuery = requestQuery;
    }

    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    @Override
    public String toString()
    {
        return "Context{" +
                "clientIP='" + clientIP + '\'' +
                ", clientPort=" + clientPort +
                ", requestHost='" + requestHost + '\'' +
                ", requestURI='" + requestURI + '\'' +
                ", requestQuery='" + requestQuery + '\'' +
                ", requestId='" + requestId + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
