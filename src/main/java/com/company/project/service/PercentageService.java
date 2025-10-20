package com.company.project.service;

import com.company.project.common.Constants;
import com.company.project.client.PercentageClient;
import com.company.project.exception.PercentageUnavailableException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PercentageService {

    private final PercentageClient percentageClient;
    private final CacheManager cacheManager;

    public double getPercentageWithCache() {
        Cache cache = cacheManager.getCache(Constants.CACHE_PERCENTAGE);
        if (cache == null) {
            throw new IllegalStateException(Constants.MSG_CACHE_NOT_CONFIGURED);
        }

        Double cached = cache.get(Constants.CACHE_KEY_PERCENTAGE, Double.class);
        if (cached != null) {
            return cached;
        }

        try {
            Double fetched = percentageClient.fetchPercentage();
            if (fetched == null) {
                throw new PercentageUnavailableException(Constants.MSG_PERCENTAGE_NOT_AVAILABLE);
            }
            cache.put(Constants.CACHE_KEY_PERCENTAGE, fetched);
            return fetched;
        } catch (RuntimeException ex) {
            Double last = cache.get(Constants.CACHE_KEY_PERCENTAGE, Double.class);
            if (Objects.nonNull(last)) {
                return last;
            }
            throw new PercentageUnavailableException(Constants.MSG_NO_CACHED_AND_EXTERNAL_FAILED);
        }
    }
}
