package com.example.commerce.catalog.core.application.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//model mapper requires args constructor
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class PriceDTO {
    private BigDecimal amount;
    private String currency;
}
