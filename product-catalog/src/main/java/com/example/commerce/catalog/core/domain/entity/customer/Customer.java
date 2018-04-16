package com.example.commerce.catalog.core.domain.entity.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = { "id" })
@AllArgsConstructor
public final class Customer extends AbstractAggregateRoot<Customer> {
	
    @Id
    private long id;
    private boolean guest;
    
}
