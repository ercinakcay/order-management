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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ea.ordermanagementapi.context.Context;
import com.ea.ordermanagementapi.context.ContextManager;
import com.ea.ordermanagementapi.util.ContextUtil;


@Component
@Order(1)
public class WebFilter implements Filter
{

    @Value("${spring.profiles.active}")
    private String env;

    private static final Logger logger = LoggerFactory.getLogger(WebFilter.class);

    @Override
    public void init(FilterConfig filterConfig)
    {
        logger.info("Event filter initiated for environment: {}", env);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest req = HttpServletRequest.class.cast(servletRequest);
        HttpServletResponse resp = HttpServletResponse.class.cast(servletResponse);

        Context ctx = ContextUtil.initializeContext(req, resp);
        try
        {
            ContextManager.set(ctx);

            logger.info("{} - Starting a transaction for request method: {} - '{}'",  ctx.getRequestId(), req.getMethod(), req.getRequestURI());

            filterChain.doFilter(req, resp);

            logger.info("{} - Ends the transaction for request method: {} - '{}'", ctx.getRequestId(), req.getMethod(), req.getRequestURI());
        }
        catch (Exception e)
        {
            logger.error("{} --- Exception occurred for request method: {} - '{}''",  ctx.getRequestId(), req.getMethod(), req.getRequestURI());
            throw e;
        }
        finally
        {
            ContextManager.clear();
        }
    }

    @Override
    public void destroy()
    {
        // just nope.
    }

}
