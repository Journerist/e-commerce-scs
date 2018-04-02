package com.example.commerce.catalog.core.application.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PriceDTO {
    private BigDecimal amount;
    private String currency;
}
