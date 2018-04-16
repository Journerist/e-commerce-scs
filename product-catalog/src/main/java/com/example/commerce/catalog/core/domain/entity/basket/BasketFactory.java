package com.example.commerce.catalog.core.domain.entity.basket;

import java.util.Collections;
import java.util.UUID;

import org.springframework.jmx.access.InvalidInvocationException;

public class BasketFactory {

	private BasketFactory() {
		throw new InvalidInvocationException("Instantiation is not allowed");
	}
	
	public static Basket createEmptyBasketFor(long customerId, String currency) {
		return new Basket(UUID.randomUUID().toString(), Collections.emptyList(), customerId, currency);
	}
}
