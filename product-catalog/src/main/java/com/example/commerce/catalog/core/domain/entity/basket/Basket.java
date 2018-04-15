package com.example.commerce.catalog.core.domain.entity.basket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.mongodb.core.index.Indexed;

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
    
    @Indexed
    private long customerId;
    
    private String currency;
    
    public Basket(String id, List<ProductLineItem> productLineItems, long customerId, String currency) {
    		this.id = id;
    		this.productLineItems = new ArrayList<>(productLineItems);
    		this.customerId = customerId;
    		this.currency = currency;
    }
    
	public boolean isEmpty() {
		return productLineItems.isEmpty();
	}

	/**
	 * Removes a product from basket
	 * 
	 * @param product
	 * 
	 * DDD:
	 * For different reasons entities and value objects of other aggregates should only store ids to other
	 * root entities. In this case a product line item is created that is kind of a copy or at least a very similar thing.
	 * It's a good example for duplicating logic instead of linking them together (non ddd approach) or to create bigger aggregates that allows
	 * object references.
	 * 
	 * For several reason there is something like an product line item in the basket for ages, long before DDD was invented. People made this decision
	 * based upon experiences. In non explored field DDD guidelines like not storing object references to non aggregates entities or value objects
	 * in combination with small aggregates instead of big ones points to the product line item solution.
	 */
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
			throw new ProductNotFoundInBasket(product);
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
	public static class ProductNotFoundInBasket extends RuntimeException {

		public ProductNotFoundInBasket(Product product) {
			super("Product coudlt not be removed from basket: " + product.toString());
		}
		
	}

}
