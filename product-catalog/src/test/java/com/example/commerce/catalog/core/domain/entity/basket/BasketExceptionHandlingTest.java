package com.example.commerce.catalog.core.domain.entity.basket;

import static com.example.commerce.catalog.core.domain.entity.product.ProductCategory.CLOTH;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.example.commerce.catalog.core.domain.entity.basket.Basket.ProductNotFoundInBasket;
import com.example.commerce.catalog.core.domain.entity.product.Price;
import com.example.commerce.catalog.core.domain.entity.product.Product;

public class BasketExceptionHandlingTest {
	
	private static final long CUSTOMER_ID = 1L;
	private static final String CURRENCY = "EUR";
	private Basket basket;
	
	private static final Product product = Product.builder()
			.sku("sku")
			.name("name")
			.price(Price.builder()
					.amount(new BigDecimal("10.00"))
					.currency("EUR")
					.build())
			.productCategory(CLOTH)
			.available(true)
			.build();

	@Before
	public void setup()  {
		basket = BasketFactory.createEmptyBasketFor(CUSTOMER_ID, CURRENCY);		
	}
	
	@Test(expected = ProductNotFoundInBasket.class)
	public void removeNonExistingItem() throws Exception {
		basket.remove(product);
	}
	
	@Test(expected = NullPointerException.class)
	public void removeNull() throws Exception {
		basket.remove(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void addNull() throws Exception {
		basket.add(null);
	}
}
