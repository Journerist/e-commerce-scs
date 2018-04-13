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
	    	createProduct("SKU1", "ABC Jeans", "10.00");
	    	createProduct("SKU2", "ABC Shorts", "15.99");
    }

	private void createProduct(String sku, String name, String price) {
		productRepository.save(
                Product.builder().sku(sku).available(true).name(name).productCategory(ProductCategory.CLOTH)
                        .price(Price.builder().amount(new BigDecimal(price)).currency("EUR").build()).build());
	}

}
