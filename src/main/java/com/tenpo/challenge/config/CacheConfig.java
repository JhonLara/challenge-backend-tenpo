package com.tenpo.challenge.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;

import com.tenpo.challenge.common.Constants;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        var cacheManager = new CaffeineCacheManager(Constants.CACHE_PERCENTAGE);
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
