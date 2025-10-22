package com.tenpo.challenge.exception;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private OffsetDateTime timestamp;
}
