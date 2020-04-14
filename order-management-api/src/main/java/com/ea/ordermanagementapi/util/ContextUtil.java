package com.ea.ordermanagementapi.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.DigestUtils;

import com.ea.ordermanagementapi.context.Context;

public class ContextUtil
{

    private static final String REQUEST_ID = "requestId";
    private static final AtomicLong requestSequence = new AtomicLong();

    public static Context initializeContext(HttpServletRequest req, HttpServletResponse resp)
    {
        Context ctx = new Context();

        ctx.setClientIP(req.getRemoteAddr());
        ctx.setClientPort(req.getRemotePort());
        ctx.setRequestHost(req.getServerName());
        ctx.setRequestId(initRequestId(resp));
        ctx.setRequestQuery(req.getQueryString());
        ctx.setRequestURI(req.getRequestURI());
        ctx.setUserAgent(getUserAgent(req));

        // -----

        return ctx;
    }


    private static String initRequestId(HttpServletResponse res)
    {
        String requestId = getRequestId();
        res.setHeader(REQUEST_ID, DigestUtils.md5DigestAsHex(requestId.getBytes()));

        return requestId;
    }

    private static String getRequestId() {
        return getPrefix() + requestSequence.incrementAndGet();
    }

    private static String getPrefix()
    {
        return "req-" + getHostName() + "-" + DateUtils.getNowDate() + "-";
    }

    private static String getHostName()
    {
        String hostName;
        try
        {
            hostName = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e)
        {
            hostName = "UnknownHostName";
        }
        return hostName;
    }

    private static String getUserAgent(HttpServletRequest req)
    {
        return req.getHeader("User-Agent") == null ? "" : req.getHeader("User-Agent");
    }
}
