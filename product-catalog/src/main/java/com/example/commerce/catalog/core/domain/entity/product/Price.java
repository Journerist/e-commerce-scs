package com.example.commerce.catalog.core.domain.entity.product;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Price {

    private final BigDecimal amount;
    private final String currency;

    public Price add(Price price) {
    		return Price.builder()
    				.amount(this.amount.add(price.getAmount()))
    				.currency(this.currency)
    				.build();
    }

	public Price multiply(int number) {
		return Price.builder()
				.amount(this.amount.multiply(new BigDecimal(number)))
				.currency(this.currency)
				.build();
	}
}
