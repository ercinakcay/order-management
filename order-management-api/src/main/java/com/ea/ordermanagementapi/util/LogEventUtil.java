package com.ea.ordermanagementapi.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.ea.ordermanagementapi.constant.DefaultConstants.HTTP_HEADERS;
import com.ea.ordermanagementapi.context.Context;
import com.ea.ordermanagementapi.context.ContextManager;
import com.ea.ordermanagementapi.event.HttpRequestLogEvent;

public class LogEventUtil
{
    // ----

    private static final ThreadLocal<HttpRequestLogEvent> threadLocal =
            ThreadLocal.withInitial(HttpRequestLogEvent::new);

    // ----

    public LogEventUtil()
    {
    }

    public void initializeLogEvent(HttpServletRequest request)
    {
        start();
        populateEvent(request);
    }

    private void populateEvent(HttpServletRequest request)
    {
        HttpRequestLogEvent event = threadLocal.get();

        event.method = request.getMethod();
        event.protocol = request.getProtocol();
        event.requestAcceptLanguage = request.getHeader(HTTP_HEADERS.ACCEPT_LANGUAGE);
        event.requestAccept = request.getHeader(HTTP_HEADERS.ACCEPT);
        event.requestAcceptEncoding = request.getHeader(HTTP_HEADERS.ACCEPT_ENCODING);
        event.requestBodySize = request.getContentLength() == -1 ? null : request.getContentLength();
    }

    private void start()
    {
        Context ctx = ContextManager.get();

        HttpRequestLogEvent event = threadLocal.get();
        event.timestampMs = System.currentTimeMillis();
        event.clientIp = ctx.getClientIP();
        event.clientPort = ctx.getClientPort();
        event.hostName = ctx.getRequestHost();
        event.query = ctx.getRequestQuery();
        event.requestURI = ctx.getRequestURI();
        event.requestId = ctx.getRequestId();
        event.userAgent = ctx.getUserAgent();
    }

    public void endLogEvent(HttpServletResponse response)
    {
        HttpRequestLogEvent event = threadLocal.get();
        event.responseContentType = response.getHeader(HTTP_HEADERS.CONTENT_TYPE);
        event.statusCode = response.getStatus();
        if (HttpStatus.resolve(response.getStatus()).isError())
        {
            event.success = false;
        }
        event.processingTimeMs = (int) (System.currentTimeMillis() - event.timestampMs);
    }

    public void handleException(Exception e)
    {
        HttpRequestLogEvent event = threadLocal.get();
        event.success = false;
        event.exceptionStackTrace = e.getMessage();
    }

    public String getHttpRequestLog()
    {
        return threadLocal.get().toString();
    }

    public void terminateLogEventData()
    {
        threadLocal.remove();
    }
}
