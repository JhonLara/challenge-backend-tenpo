package com.company.project.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HistoryRecordDto {
    private Long id;
    private OffsetDateTime createdAt;
    private String endpoint;
    private String parameters;
    private String response;
    private String error;
}
