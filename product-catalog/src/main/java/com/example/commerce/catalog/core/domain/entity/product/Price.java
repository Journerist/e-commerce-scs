package com.example.commerce.catalog.core.domain.entity.product;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Price {

    private final BigDecimal amount;
    private final String currency;

    public Price add(Price p) {
    		return Price.builder()
    				.amount(this.amount.add(p.getAmount()))
    				.currency(this.currency)
    				.build();
    }

	public Price multiply(int i) {
		return Price.builder()
				.amount(this.amount.multiply(new BigDecimal(i)))
				.currency(this.currency)
				.build();
	}
}
