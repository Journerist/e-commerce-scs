package com.example.commerce.catalog.core.domain.entity.product;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Price {

	private final BigDecimal amount;
	private final String currency;
	
}
