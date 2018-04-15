package com.example.commerce.catalog.core.domain.entity.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, of = { "id" })
@AllArgsConstructor
@NoArgsConstructor  //TODO: get rid of it and avoid using the default constructor. This is necessry due to spring data as soon as a 
					// a customer is fetched from a repository
public final class Customer extends AbstractAggregateRoot<Customer> {
	
    @Id
    private long id;
    private boolean guest;

}
