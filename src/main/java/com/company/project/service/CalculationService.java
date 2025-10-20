package com.company.project.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final PercentageService percentageService;

    public Result calculate(BigDecimal num1, BigDecimal num2) {
        var base = num1.add(num2);
        var percentage = BigDecimal.valueOf(percentageService.getPercentageWithCache());
        var multiplier = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP));
        var result = base.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
        return new Result(result, percentage.doubleValue());
    }

    public record Result(BigDecimal value, double percentageApplied) {}
}
