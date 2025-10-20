package com.tenpo.challenge.service;

import com.tenpo.challenge.entity.HistoryRecord;
import com.tenpo.challenge.repository.HistoryRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

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
