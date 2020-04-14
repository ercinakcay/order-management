package com.ea.ordermanagementapi.configurator;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.ea.ordermanagementapi.aspect.TransactionAspect;
import com.ea.ordermanagementapi.filter.LoggingEventFilter;
import com.ea.ordermanagementapi.mapper.ApiObjectMapper;

@Configuration
public class BaseConfiguration
{
    @Bean
    public MessageSource messageResources()
    {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public FilterRegistrationBean<LoggingEventFilter> loggingFilter()
    {
        FilterRegistrationBean<LoggingEventFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggingEventFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public ApiObjectMapper apiObjectMapper()
    {
        return new ApiObjectMapper();
    }

    @Bean
    public TransactionAspect transactionAspect()
    {
        return new TransactionAspect();
    }
}
