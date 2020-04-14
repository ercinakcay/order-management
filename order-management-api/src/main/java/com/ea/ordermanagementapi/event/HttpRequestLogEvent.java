package com.ea.ordermanagementapi.event;

/**
 * Note : @{@link Deprecated} variables not implemented for event filter.
 */
public class HttpRequestLogEvent
{

    public Long timestampMs;
    public boolean success = true;
    public Integer clientPort;
    public Integer processingTimeMs;
    public Integer requestBodySize;
    public Integer statusCode;
    @Deprecated
    public Long userId;
    public String clientIp;
    public String exceptionStackTrace;
    public String hostName;
    public String method;
    public String protocol;
    public String query;
    public String requestAccept;
    public String requestAcceptEncoding;
    public String requestAcceptLanguage;
    public String requestId;
    public String requestURI;
    public String responseContentType;
    public String userAgent;

    @Override
    public String toString()
    {
        return "HttpRequestLogEvent{" +
                "timestampMs=" + timestampMs +
                ", success=" + success +
                ", clientPort=" + clientPort +
                ", processingTimeMs=" + processingTimeMs +
                ", requestBodySize=" + requestBodySize +
                ", statusCode=" + statusCode +
                ", userId=" + userId +
                ", clientIp='" + clientIp + '\'' +
                ", exceptionStackTrace='" + exceptionStackTrace + '\'' +
                ", hostName='" + hostName + '\'' +
                ", method='" + method + '\'' +
                ", protocol='" + protocol + '\'' +
                ", query='" + query + '\'' +
                ", requestAccept='" + requestAccept + '\'' +
                ", requestAcceptEncoding='" + requestAcceptEncoding + '\'' +
                ", requestAcceptLanguage='" + requestAcceptLanguage + '\'' +
                ", requestId='" + requestId + '\'' +
                ", requestURI='" + requestURI + '\'' +
                ", responseContentType='" + responseContentType + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
