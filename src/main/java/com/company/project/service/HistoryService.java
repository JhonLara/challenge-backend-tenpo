package com.company.project.service;

import com.company.project.entity.HistoryRecord;
import com.company.project.repository.HistoryRecordRepository;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRecordRepository repository;

    @Async
    public void logAsync(String endpoint, String parameters, String response, String error) {
        var r = new HistoryRecord();
        r.setCreatedAt(OffsetDateTime.now());
        r.setEndpoint(endpoint);
        r.setParameters(parameters);
        r.setResponse(response);
        r.setError(error);
        repository.save(r);
    }
}
