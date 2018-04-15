package com.example.commerce.catalog.core.domain.entity.basket;

import java.util.Collections;
import java.util.UUID;

public class BasketFactory {

	public static Basket createEmptyBasketFor(long customerId, String currency) {
		return new Basket(UUID.randomUUID().toString(), Collections.emptyList(), customerId, currency);
	}
}
