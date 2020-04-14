package com.ea.ordermanagementapi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ea.ordermanagementapi.util.LogEventUtil;

@Component
@Order(2)
public class LoggingEventFilter implements Filter
{

    private static final Logger logger = LoggerFactory.getLogger(LoggingEventFilter.class);

    private LogEventUtil logEventUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.logEventUtil = new LogEventUtil();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest req = HttpServletRequest.class.cast(servletRequest);
        HttpServletResponse resp = HttpServletResponse.class.cast(servletResponse);

        try
        {
            logEventUtil.initializeLogEvent(req);

            // ----

            filterChain.doFilter(req, resp);

            // ----
        }
        catch (Exception e)
        {
            logEventUtil.handleException(e);
            throw e;
        }
        finally
        {
            logEventUtil.endLogEvent(resp);

            // ----

            logger.info(logEventUtil.getHttpRequestLog());

            // ----

            logEventUtil.terminateLogEventData();
        }

    }

}

