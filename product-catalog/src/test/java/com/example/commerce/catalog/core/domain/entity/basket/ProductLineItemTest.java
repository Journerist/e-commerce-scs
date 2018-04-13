package com.example.commerce.catalog.core.domain.entity.basket;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ProductLineItemTest {

	@Test
	public void contractTest() throws Exception {
		EqualsVerifier.forClass(ProductLineItem.class).verify();
	}
	
}
