package com.example.commerce.catalog.core.domain.entity.basket;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketRepository extends MongoRepository<Basket, String> {

	Optional<Basket> findByCustomerId(long customerId);
	
}
