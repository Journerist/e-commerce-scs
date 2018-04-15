package com.example.commerce.catalog.core.application;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import com.example.commerce.catalog.core.application.dto.BasketDTO;
import com.example.commerce.catalog.core.application.dto.PriceDTO;
import com.example.commerce.catalog.core.domain.entity.basket.Basket;
import com.example.commerce.catalog.core.domain.entity.basket.BasketFactory;
import com.example.commerce.catalog.core.domain.entity.basket.BasketRepository;
import com.example.commerce.catalog.core.domain.entity.product.Price;
import com.example.commerce.catalog.core.domain.entity.product.Product;
import com.example.commerce.catalog.core.domain.entity.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
public class BasketApplicationService {

	private final BasketRepository basketRepository;
	private final ProductRepository productRepository;
	
	private final ModelMapper modelMapper;

	public void addItemToBasket(String basketId) {
		// basketRepository.find()
	}

	public BasketDTO createOrGetExistingBasketFor(long customerId) {
		Basket basket = getBasketFromRepository(customerId);
		return toDTO(basket);
	}

	private BasketDTO toDTO(Basket basket) {
		return modelMapper.map(basket, BasketDTO.class);
	}

	public BasketDTO addProduct(long customerId, String productSku) {
		Basket basket = getBasketFromRepository(customerId);
		
		Product product = getProductFromRepository(productSku);
		
		basket.add(product);
		basketRepository.save(basket);
		
		return toDTO(basket);
	}

	public BasketDTO removeProduct(long customerId, String sku) {
		Basket basket = getBasketFromRepository(customerId);
		Product product = getProductFromRepository(sku);
		basket.remove(product);
		return toDTO(basket);
	}
	
	public PriceDTO calculateBasket(long customerId) {
		Basket basket = getBasketFromRepository(customerId);
		Price basketPrice = basket.caluclate();
		return modelMapper.map(basketPrice, PriceDTO.class);
	}

	private Product getProductFromRepository(String productSku) {
		Optional<Product> product = productRepository.findById(productSku);
		if(!product.isPresent()) {
			throw new ProductNotFoundInShop(productSku);
		}
		return product.get();
	}

	private Basket getBasketFromRepository(long customerId) {
		Basket basketToMap;
		Optional<Basket> basket = basketRepository.findByCustomerId(customerId);
		
		if (basket.isPresent()) {
			basketToMap = basket.get();
		} else {
			basketToMap = BasketFactory.createEmptyBasketFor(customerId, "EUR");
		}
		
		if(!basket.isPresent()) {			
			basketRepository.save(basketToMap);
		}
		return basketToMap;
	}
	
	@SuppressWarnings("serial") // never serialised
	public static final class ProductNotFoundInShop extends RuntimeException {
		public ProductNotFoundInShop(String sku) {
			super("Product with sku '" + sku + "' does not exist");
		}
	}

}
