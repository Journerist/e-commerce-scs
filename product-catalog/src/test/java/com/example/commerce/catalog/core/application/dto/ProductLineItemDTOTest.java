package com.example.commerce.catalog.core.application.dto;

import java.math.BigDecimal;

import org.junit.Test;

public class ProductLineItemDTOTest {

	@Test
	public void toStringWorks() throws Exception {
		ProductLineItemDTO productLineItemDTO = new ProductLineItemDTO("sku", "name", 0, new PriceDTO(new BigDecimal("10.00"), "EUR"));
		productLineItemDTO.toString();
	}
	
}
