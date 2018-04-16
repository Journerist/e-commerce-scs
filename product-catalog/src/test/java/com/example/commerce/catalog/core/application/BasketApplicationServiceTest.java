package com.example.commerce.catalog.core.application;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.commerce.catalog.core.application.BasketApplicationService.ProductNotFoundInShop;
import com.example.commerce.catalog.core.application.dto.BasketDTO;
import com.example.commerce.catalog.core.application.dto.PriceDTO;
import com.example.commerce.catalog.core.domain.entity.basket.Basket.ProductNotFoundInBasket;
import com.example.commerce.catalog.core.domain.entity.basket.BasketRepository;
import com.example.commerce.catalog.core.domain.entity.customer.CustomerRepository;
import com.example.commerce.catalog.core.domain.entity.product.Price;
import com.example.commerce.catalog.core.domain.entity.product.Product;
import com.example.commerce.catalog.core.domain.entity.product.ProductCategory;
import com.example.commerce.catalog.core.domain.entity.product.ProductRepository;
import com.example.commerce.catalog.core.infrastructure.DemoApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
@DataMongoTest
@EnableMongoRepositories(basePackageClasses = {CustomerRepository.class, BasketRepository.class})
@DirtiesContext
public class BasketApplicationServiceTest {

	private BasketApplicationService basketService;
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private final static String PRODUCT_PRICE = "10.00";
	
	@Before
	public void setup() {
		basketRepository.deleteAll();
		productRepository.deleteAll();
		basketService = new BasketApplicationService(basketRepository, productRepository, modelMapper);
		
		productRepository.save(Product.builder()
				.sku("SKU1")
				.available(true)
				.name("Shorts")
				.productCategory(ProductCategory.CLOTH)
				.price(new Price(new BigDecimal(PRODUCT_PRICE), "EUR"))
				.build());
				
	}
	
	@Test
	public void customerWithNoBasket_createOrGetExistingBasketFor_createsEmptyBasket() throws Exception {
		long customerId = 1L;
		BasketDTO basket = basketService.createOrGetExistingBasketFor(customerId);

		Assert.assertEquals(0, basket.getProductLineItems().size());
	}
	
	@Test
	public void customerWithExistingBasket_createOrGetExistingBasketFor_returnsTheExistingBasket() throws Exception {
		long customerId = 1L;
		
		// create new basket
		BasketDTO newBasket = basketService.createOrGetExistingBasketFor(customerId);

		// should return an existing basket
		BasketDTO existingBasket = basketService.createOrGetExistingBasketFor(customerId);
		
		Assert.assertEquals(newBasket.getId(), existingBasket.getId());
	}
	
	@Test
	public void newCustomer_addProduct_createsNewBasketWithSingleProduct() throws Exception {
		long customerId = 1L;
		BasketDTO basket = basketService.addProduct(customerId, "SKU1");
		
		Assert.assertEquals(1, basket.getProductLineItems().size());
	}
	
	@Test(expected = ProductNotFoundInShop.class)
	public void notExistingProduct_addProduct_throwsException() throws Exception {
		long customerId = 1L;
		basketService.addProduct(customerId, "SKU99");
	}
	
	@Test(expected = ProductNotFoundInShop.class)
	public void emptyBasket_removeNotExistingProduct_throwsException() throws Exception {
		long customerId = 1L;
		basketService.removeProduct(customerId, "SKU99");
	}
	
	@Test(expected = ProductNotFoundInBasket.class)
	public void emptyBasket_removeExistingProductThatIsNotInTheBasket_throwsException() throws Exception {
		long customerId = 1L;
		basketService.removeProduct(customerId, "SKU1");
	}
	
	@Test
	public void basketWithNoProducts_calculcateBasketPrice_returnsZeroIfEmpty() throws Exception {
		long customerId = 1L;
		PriceDTO basketPrice = basketService.calculateBasket(customerId);
		Assert.assertEquals(new PriceDTO(BigDecimal.ZERO, "EUR"), basketPrice);
	}
	
	@Test
	public void basketWithProducts_calculcateBasketPrice_returnsTheItemPrice() throws Exception {
		long customerId = 1L;
		basketService.addProduct(customerId, "SKU1");
		PriceDTO basketPrice = basketService.calculateBasket(customerId);
		Assert.assertEquals(new PriceDTO(new BigDecimal(PRODUCT_PRICE), "EUR"), basketPrice);
	}
	
}
