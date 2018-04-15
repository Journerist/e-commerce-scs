package com.example.commerce.catalog.core.application.dto;

import java.util.Collections;

import org.junit.Test;

public class BasketDTOTest {

	@Test
	public void toStringWorks() throws Exception {
		new BasketDTO("id", Collections.emptyList(), "customerId", "EUR");
	}
	
}
