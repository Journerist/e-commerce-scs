package com.example.commerce.catalog.core.domain.entity.basket;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, of = { "id" })
@AllArgsConstructor
public class Basket extends AbstractAggregateRoot<Basket> {

    @Id
    private String id;
    private List<String> productSkus;
    private String customerId;

}
