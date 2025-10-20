package com.tenpo.challenge.service;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.tenpo.challenge.client.PercentageClient;
import com.tenpo.challenge.common.Constants;
import com.tenpo.challenge.exception.PercentageUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PercentageServiceTest {

    private static final String KEY = "percentage";
    private static final String ERROR = "x";

    @Mock
    private PercentageClient percentageClient;

    private CacheManager cacheManager;

    @InjectMocks
    private PercentageService percentageService;

    @BeforeEach
    void setup() {
        var caffeine = Caffeine.newBuilder();
        var manager = new CaffeineCacheManager(Constants.CACHE_PERCENTAGE);
        manager.setCaffeine(caffeine);
        cacheManager = manager;
        percentageService = new PercentageService(percentageClient, cacheManager);
        Cache cache = cacheManager.getCache(Constants.CACHE_PERCENTAGE);
        if (cache != null) {
            cache.clear();
        }
    }

    @Test
    void givenCacheEmptyAndClientOk_whenGetPercentage_thenReturnsAndCaches() {
        when(percentageClient.fetchPercentage()).thenReturn(12.5);
        var value = percentageService.getPercentageWithCache();
        assertEquals(12.5, value);
        var cached = cacheManager.getCache(Constants.CACHE_PERCENTAGE).get(KEY, Double.class);
        assertEquals(12.5, cached);
        verify(percentageClient).fetchPercentage();
    }

    @Test
    void givenCacheHasValue_whenGetPercentage_thenReturnsCachedAndSkipsClient() {
        var cache = cacheManager.getCache(Constants.CACHE_PERCENTAGE);
        cache.put(KEY, 15.0);
        var value = percentageService.getPercentageWithCache();
        assertEquals(15.0, value);
        verify(percentageClient, never()).fetchPercentage();
    }

    @Test
    void givenClientFailsAndCacheHasValue_whenGetPercentage_thenReturnsCached() {
        var cache = cacheManager.getCache(Constants.CACHE_PERCENTAGE);
        cache.put(KEY, 8.0);
        when(percentageClient.fetchPercentage()).thenThrow(new RuntimeException(ERROR));
        var value = percentageService.getPercentageWithCache();
        assertEquals(8.0, value);
    }

    @Test
    void givenClientFailsAndNoCache_whenGetPercentage_thenThrowsUnavailable() {
        when(percentageClient.fetchPercentage()).thenThrow(new RuntimeException(ERROR));
        assertThrows(PercentageUnavailableException.class, () -> percentageService.getPercentageWithCache());
    }
}
