package com.example.commerce.catalog.core.application.dto;

import java.math.BigDecimal;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class PriceDTOTest {

    @Test
    public void contract() throws Exception {
        EqualsVerifier.forClass(PriceDTO.class)
        .verify();
    }
    
    @Test
    public void toStringWorks() throws Exception {
        PriceDTO priceDTO = new PriceDTO(new BigDecimal("1.0"), "EUR");
        priceDTO.toString();
    }
    
}
