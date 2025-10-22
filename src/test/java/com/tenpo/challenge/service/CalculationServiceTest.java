package com.tenpo.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    private static final String HUNDRED = "100";
    private static final String FIFTY = "50";
    private static final String ZERO = "0";
    private static final String NEGATIVE_TEN = "-10";

    @Mock
    private PercentageService percentageService;

    @InjectMocks
    private CalculationService calculationService;

    @Test
    void givenTwoNumbersAndTenPercent_whenCalculate_thenAppliesPercentageAndRounds() {
        when(percentageService.getPercentageWithCache()).thenReturn(10.0);
        final var num1 = new BigDecimal(HUNDRED);
        final var num2 = new BigDecimal(FIFTY);
        final var result = calculationService.calculate(num1, num2);
        assertEquals(new BigDecimal("165.00"), result.value());
        assertEquals(10.0, result.percentageApplied());
    }

    @Test
    void givenZerosAndZeroPercent_whenCalculate_thenReturnsZero() {
        when(percentageService.getPercentageWithCache()).thenReturn(0.0);
        final var num1 = new BigDecimal(ZERO);
        final var num2 = new BigDecimal(ZERO);
        final var result = calculationService.calculate(num1, num2);
        assertEquals(new BigDecimal("0.00"), result.value());
        assertEquals(0.0, result.percentageApplied());
    }

    @Test
    void givenNegativeAndPositive_whenCalculate_thenHandlesCorrectly() {
        when(percentageService.getPercentageWithCache()).thenReturn(5.0);
        final var num1 = new BigDecimal(NEGATIVE_TEN);
        final var num2 = new BigDecimal(HUNDRED);
        final var result = calculationService.calculate(num1, num2);
        assertEquals(new BigDecimal("94.50"), result.value());
        assertEquals(5.0, result.percentageApplied());
    }
}
