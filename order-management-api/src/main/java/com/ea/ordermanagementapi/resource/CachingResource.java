package com.ea.ordermanagementapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.ea.ordermanagementapi.service.CacheService;
import com.ea.ordermanagementapi.util.rest.Resource;
import com.ea.ordermanagementapi.util.rest.Response;

@Resource("/cache")
public class CachingResource
{
    private CacheService cacheService;

    @Autowired
    public CachingResource(CacheService cacheService)
    {
        this.cacheService = cacheService;
    }

    @GetMapping("/evictAll")
    public Response evictAllCaches()
    {
        cacheService.evictAllCaches();
        return new Response(true);
    }
}