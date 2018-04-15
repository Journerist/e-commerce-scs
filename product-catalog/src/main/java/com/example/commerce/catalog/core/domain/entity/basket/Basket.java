package com.example.commerce.catalog.core.domain.entity.basket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.example.commerce.catalog.core.domain.entity.product.Price;
import com.example.commerce.catalog.core.domain.entity.product.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = { "id" })
public class Basket extends AbstractAggregateRoot<Basket> {

    @Id
    private String id;
    private List<ProductLineItem> productLineItems = new ArrayList<>();
    private String customerId;
    private String currency;
    
    public Basket(String id, List<ProductLineItem> productLineItems, String customerId, String currency) {
    		this.id = id;
    		this.productLineItems = new ArrayList<>(productLineItems);
    		this.customerId = customerId;
    		this.currency = currency;
    }
    
	public boolean isEmpty() {
		return productLineItems.isEmpty();
	}

	public void add(Product product) {
		Objects.requireNonNull(product, "product");
		Optional<ProductLineItem> pliInBasket = getProductLineItemInBasketBy(product);
		
		if(pliInBasket.isPresent()) {
			productLineItems.remove(pliInBasket.get());
			productLineItems.add(pliInBasket.get().increase(1));
		} else {
			productLineItems.add(ProductLineItem.from(product));
		}
		
	}

	public int productCount() {
		return productLineItems.size();
	}

	public void remove(Product product) {
		Objects.requireNonNull(product, "product");
		Optional<ProductLineItem> pli = getProductLineItemInBasketBy(product);
		
		if(!pli.isPresent()) {
			throw new ProductNotFound(product);
		}
		
		if(pli.isPresent()) {
			productLineItems.remove(pli.get());
			
			int currentPliAmount = pli.get().getAmount();
			if(currentPliAmount > 1) {
				ProductLineItem pliWithDecreasedAmount = pli.get().createBuilderFromInstance()
					.amount(currentPliAmount - 1)
					.build();
				productLineItems.add(pliWithDecreasedAmount);
			}
		}
		productLineItems.remove(ProductLineItem.from(product));
	}

	public Price caluclate() {
			return productLineItems.stream()
					.map(pli -> pli.getPrice().multiply(pli.getAmount()))
					.reduce( (price1, price2) -> price1.add(price2))
					.orElse(Price.builder().amount(BigDecimal.ZERO).currency(currency).build());
	}
	
	private Optional<ProductLineItem> getProductLineItemInBasketBy(Product product) {
		Optional<ProductLineItem> pliInBasket = productLineItems.stream()
			.filter( pli -> pli.getSku().equals(product.getSku()))
			.findFirst();
		return pliInBasket;
	}
	
	@SuppressWarnings("serial") // will never be serialised
	public static class ProductNotFound extends RuntimeException {

		public ProductNotFound(Product product) {
			super("Product coudlt not be removed from basket: " + product.toString());
		}
		
	}

}
