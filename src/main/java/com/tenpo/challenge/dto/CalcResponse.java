package com.tenpo.challenge.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalcResponse {
    private BigDecimal result;
    private Double appliedPercentage;
    private String percentageSource;
}
