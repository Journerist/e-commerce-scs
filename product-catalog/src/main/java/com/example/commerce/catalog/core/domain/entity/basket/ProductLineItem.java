package com.example.commerce.catalog.core.domain.entity.basket;

import com.example.commerce.catalog.core.domain.entity.product.Price;
import com.example.commerce.catalog.core.domain.entity.product.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ProductLineItem {
	
    private final String sku;
    private final String name;
    private final int amount;
    private final Price price;
    
    /**
     * Create a single product line item instance of a product.
     * 
     * @param product
     * @return
     */
	public static ProductLineItem from(Product product) {
		return ProductLineItem.builder()
				.amount(1)
				.name(product.getName())
				.price(product.getPrice())
				.sku(product.getSku())
				.build(); 
	}

	public ProductLineItem increase(int number) {
		return this.createBuilderFromInstance()
				.amount(amount + number)
				.build();
		
	}
	
	public ProductLineItemBuilder createBuilderFromInstance() {
		return ProductLineItem.builder()
			.amount(amount)
			.name(name)
			.price(price)
			.sku(sku);
	}
	
}
