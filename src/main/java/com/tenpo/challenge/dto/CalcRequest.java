package com.tenpo.challenge.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CalcRequest {
    @NotNull
    private BigDecimal num1;

    @NotNull
    private BigDecimal num2;
}
