package com.tenpo.challenge.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalcRequest {
    @NotNull
    private BigDecimal num1;

    @NotNull
    private BigDecimal num2;
}
