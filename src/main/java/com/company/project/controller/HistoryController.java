package com.company.project.controller;

import com.company.project.common.Constants;
import com.company.project.dto.HistoryRecordDto;
import com.company.project.entity.HistoryRecord;
import com.company.project.repository.HistoryRecordRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.ENDPOINT_HISTORY)
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryRecordRepository repository;

    @GetMapping
    public ResponseEntity<Page<HistoryRecordDto>> list(
            @RequestParam(name = Constants.PARAM_PAGE, defaultValue = "0") int page,
            @RequestParam(name = Constants.PARAM_SIZE, defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, Math.max(1, Math.min(size, 100)));
        Page<HistoryRecord> result = repository.findAll(pageable);
        List<HistoryRecordDto> items = result
                .getContent()
                .stream()
                .map(r -> new HistoryRecordDto(
                        r.getId(),
                        r.getCreatedAt(),
                        r.getEndpoint(),
                        r.getParameters(),
                        r.getResponse(),
                        r.getError()))
                .collect(Collectors.toList());
        Page<HistoryRecordDto> response = new PageImpl<>(items, pageable, result.getTotalElements());
        return ResponseEntity.ok(response);
    }
}
