package com.example.commerce.catalog.core.domain.entity.basket;

import static com.example.commerce.catalog.core.domain.entity.product.ProductCategory.CLOTH;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.example.commerce.catalog.core.domain.entity.product.Price;
import com.example.commerce.catalog.core.domain.entity.product.Product;
import com.example.commerce.catalog.core.domain.entity.product.ProductCategory;


public class BasketTest {

	private static final String CUSTOMER_ID = "CustomerId";
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
	
	private static String CURRENCY = "EUR";

	@Before
	public void setup()  {
		basket = BasketFactory.createEmptyBasketFor(CUSTOMER_ID, CURRENCY);		
	}
	
	@Test
	public void initialBasketIsEmpty() throws Exception {
		assertEquals(true, basket.isEmpty());
	}
	
	@Test
	public void cutomerForTheBasketIsSaved() throws Exception {
		assertEquals(CUSTOMER_ID, basket.getCustomerId());
	}
	
	@Test
	public void emptyProduct_productAdded_containsOneItem() throws Exception {
		basket.add(product);
		assertEquals(1, basket.productCount());
	}
	
	@Test
	public void emptyProduct_productAdded_containsExactlyThisItem() throws Exception {
		basket.add(product);
		
		ProductLineItem pli = ProductLineItem.from(product);
		
		assertEquals(Arrays.asList(pli), basket.getProductLineItems());
	}
	
	@Test
	public void emptyProduct_productAddedTwoTimes_containsExactlyThisItemWithAmountTwo() throws Exception {
		basket.add(product);
		basket.add(product);
		
		
		ProductLineItem pli = ProductLineItem.from(product);
		ProductLineItem pliItemTwoTimes = pli.createBuilderFromInstance()
			.amount(2)
			.build();
		
		assertEquals(Arrays.asList(pliItemTwoTimes), basket.getProductLineItems());
	}
	
	@Test
	public void singleProductBasket_productRemoved_isEmpty() throws Exception {
		basket.add(product);
		basket.remove(product);
		assertEquals(true, basket.isEmpty());
	}
	
	@Test
	public void emptyBasket_calculate_isZero() throws Exception {
		Price zero = Price.builder().amount(BigDecimal.ZERO).currency(CURRENCY).build();
		assertEquals(zero, basket.caluclate());
	}
	
	@Test
	public void singleItemBasket_calculate_isItemPrice() throws Exception {
		Product product = Product.builder()
			.sku("SKU1")
			.price(Price.builder()
					.amount(new BigDecimal("10.00"))
					.currency(CURRENCY)
					.build())
			.available(true)
			.productCategory(ProductCategory.CLOTH)
			.build();
		basket.add(product);
		
		assertEquals(product.getPrice(), basket.caluclate());
	}
	
	@Test
	public void twoItemsInBasket_calculate_isSumOfItemPrice() throws Exception {
		Product product1 = Product.builder()
				.sku("SKU1")
				.price(Price.builder()
						.amount(new BigDecimal("10.00"))
						.currency(CURRENCY)
						.build())
				.available(true)
				.productCategory(ProductCategory.CLOTH)
				.build();
		
		Product product2 = Product.builder()
				.sku("SKU2")
				.price(Price.builder()
						.amount(new BigDecimal("20.00"))
						.currency(CURRENCY)
						.build())
				.available(true)
				.productCategory(ProductCategory.CLOTH)
				.build();
		basket.add(product1);
		basket.add(product2);
		
		assertEquals(product1.getPrice().add(product2.getPrice()), basket.caluclate());
	}
	
	@Test
	public void oneItemTwoTimesInBasket_calculate_isSumOfItemPrices() throws Exception {
		Product product = Product.builder()
			.sku("SKU1")
			.price(Price.builder()
					.amount(new BigDecimal("10.00"))
					.currency(CURRENCY)
					.build())
			.available(true)
			.productCategory(ProductCategory.CLOTH)
			.build();
		basket.add(product);
		basket.add(product);
		
		assertEquals(product.getPrice().add(product.getPrice()), basket.caluclate());
	}

}
