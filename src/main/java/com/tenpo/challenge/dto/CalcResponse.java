package com.tenpo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CalcResponse {
    private BigDecimal result;
    private Double appliedPercentage;
    private String percentageSource;
}
