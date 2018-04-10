package com.example.commerce.catalog.core.application.dto;

import java.math.BigDecimal;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ProductDTOTest {

    @Test
    public void contract() throws Exception {
        EqualsVerifier.forClass(ProductDTO.class)
        .verify();
    }
    
    @Test
    public void toStringWorks() throws Exception {
        ProductDTO productDTO = new ProductDTO("sku", "name", new PriceDTO(new BigDecimal("1.0"), "EUR"), "Category", true);
        productDTO.toString();
    }
    
}
