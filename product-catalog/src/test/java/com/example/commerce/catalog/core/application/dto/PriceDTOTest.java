package com.example.commerce.catalog.core.application.dto;

import java.math.BigDecimal;

import org.junit.Test;

public class PriceDTOTest {

    @Test
    public void toStringWorks() throws Exception {
        PriceDTO priceDTO = new PriceDTO(new BigDecimal("1.0"), "EUR");
        priceDTO.toString();
    }
    
}
