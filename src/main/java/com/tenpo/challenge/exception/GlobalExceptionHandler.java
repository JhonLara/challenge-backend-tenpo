package com.tenpo.challenge.exception;

import com.tenpo.challenge.common.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PercentageUnavailableException.class)
    public ResponseEntity<ErrorResponse> handlePercentageUnavailable(PercentageUnavailableException ex) {
        var body = new ErrorResponse(Constants.ERR_PERCENTAGE_UNAVAILABLE, ex.getMessage(), OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        var body = new ErrorResponse(Constants.ERR_VALIDATION, Constants.MSG_INVALID_REQUEST, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        var body = new ErrorResponse(Constants.ERR_BAD_REQUEST, ex.getMessage(), OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        var body = new ErrorResponse(Constants.ERR_INTERNAL, Constants.MSG_UNEXPECTED_ERROR, OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
