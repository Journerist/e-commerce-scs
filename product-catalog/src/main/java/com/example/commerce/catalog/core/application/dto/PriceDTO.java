package com.example.commerce.catalog.core.application.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Value;

@Value
public final class PriceDTO {
    private final BigDecimal amount;
    private final String currency;
}
