package com.example.commerce.catalog.core.domain.entity.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false, of={"sku"})
@Builder
public class Product extends AbstractAggregateRoot<Product>{

	@Id
	private final String sku;
	private final String name;
	private final Price price;
	private final ProductCategory productCategory;
	private final boolean available;
	
}
