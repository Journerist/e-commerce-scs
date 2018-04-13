package com.example.commerce.catalog.core.domain.entity.basket;

import java.util.Collections;
import java.util.UUID;

public class BasketFactory {

	public static Basket createEmptyBasketFor(String customerId, String currency) {
		return new Basket(UUID.randomUUID().toString(), Collections.emptyList(), customerId, currency);
	}
}
