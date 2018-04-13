package com.example.commerce.catalog.core.application.dto;

import java.math.BigDecimal;

import org.junit.Test;

public class ProductDTOTest {

    @Test
    public void toStringWorks() throws Exception {
        ProductDTO productDTO = new ProductDTO("sku", "name", new PriceDTO(new BigDecimal("1.0"), "EUR"), "Category", true);
        productDTO.toString();
    }
    
}
