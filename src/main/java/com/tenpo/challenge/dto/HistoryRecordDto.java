package com.tenpo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

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
