package com.company.project.client;

import com.company.project.exception.ExternalServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MockPercentageClient implements PercentageClient {

    private final double fixedPercentage;
    private final boolean failMode;

    public MockPercentageClient(
            @Value("${external.percentage.fixed:10}") double fixedPercentage,
            @Value("${external.percentage.fail:false}") boolean failMode) {
        this.fixedPercentage = fixedPercentage;
        this.failMode = failMode;
    }

    @Override
    public Double fetchPercentage() {
        if (failMode) {
            throw new ExternalServiceException("External percentage service unavailable");
        }
        return fixedPercentage;
    }
}
