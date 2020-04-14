package com.ea.ordermanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiApplication
{
    public static void main(String[] args)
    {
        System.setProperty("spring.main.lazy-initialization", "true");
        SpringApplication.run(ApiApplication.class, args);
    }
}
