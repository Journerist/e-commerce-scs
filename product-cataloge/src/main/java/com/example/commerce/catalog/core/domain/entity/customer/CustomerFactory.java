package com.example.commerce.catalog.core.domain.entity.customer;

import org.springframework.stereotype.Component;

import com.example.commerce.catalog.core.infrastructure.db.SequenceGenerator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerFactory {

	private final SequenceGenerator sequenceGenerator;
	
	public Customer createNew() {
		return new Customer(sequenceGenerator.getNextSequence("customer"), true);
	}
	
}
