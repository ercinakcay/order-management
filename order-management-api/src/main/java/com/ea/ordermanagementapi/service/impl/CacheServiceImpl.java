package com.ea.ordermanagementapi.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.ea.ordermanagementapi.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService
{
    CacheManager cacheManager;

    @Autowired
    public CacheServiceImpl(CacheManager cacheManager)
    {
        this.cacheManager = cacheManager;
    }

    @Override
    public void evictAllCaches()
    {
        cacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName))
                .clear());
    }
}
