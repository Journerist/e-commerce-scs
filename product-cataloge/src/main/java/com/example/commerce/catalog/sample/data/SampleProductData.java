package com.example.commerce.catalog.sample.data;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.commerce.catalog.core.domain.entity.product.Price;
import com.example.commerce.catalog.core.domain.entity.product.Product;
import com.example.commerce.catalog.core.domain.entity.product.ProductCategory;
import com.example.commerce.catalog.core.domain.entity.product.ProductRepository;

@Component
public class SampleProductData {

	private final ProductRepository productRepository;
	
	@Autowired
	public SampleProductData(ProductRepository productRepository) {
		this.productRepository = productRepository;
		fillDatabase();
	}

	public void fillDatabase() {
		productRepository.save(
				Product.builder()
					.sku("SKU1")
					.available(true)
					.name("ABC Jeans")
					.productCategory(ProductCategory.CLOTH)
					.price(
							Price.builder()
								.amount(new BigDecimal("10.00"))
								.currency("EUR")
								.build())
					.build()
		);
		
		productRepository.save(
				Product.builder()
					.sku("SKU2")
					.available(true)
					.name("ABC Shorts")
					.productCategory(ProductCategory.CLOTH)
					.price(
							Price.builder()
								.amount(new BigDecimal("15.99"))
								.currency("EUR")
								.build())
					.build()
		);
	}
	
}
