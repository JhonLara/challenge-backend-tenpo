package com.tenpo.challenge.controller;

import com.tenpo.challenge.common.Constants;
import com.tenpo.challenge.dto.CalcRequest;
import com.tenpo.challenge.dto.CalcResponse;
import com.tenpo.challenge.service.CalculationService;
import com.tenpo.challenge.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(Constants.ENDPOINT_CALC)
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;
    private final HistoryService historyService;

    @PostMapping
    public ResponseEntity<CalcResponse> calculate(@Valid @RequestBody CalcRequest request) {
        try {
            var result = calculationService.calculate(request.getNum1(), request.getNum2());
            var response = new CalcResponse(result.value(), result.percentageApplied(),
                    Constants.PERCENTAGE_SOURCE_CACHE_OR_EXTERNAL);
            historyService.logAsync(Constants.ENDPOINT_CALC, serializeParams(request.getNum1(), request.getNum2()),
                    response.getResult().toPlainString(), null);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            historyService.logAsync(Constants.ENDPOINT_CALC, serializeParams(request.getNum1(), request.getNum2()),
                    null, ex.getMessage());
            throw ex;
        }
    }

    private String serializeParams(BigDecimal num1, BigDecimal num2) {
        return "num1=" + (num1 == null ? "null" : num1.toPlainString()) + ",num2="
                + (num2 == null ? "null" : num2.toPlainString());
    }
}
