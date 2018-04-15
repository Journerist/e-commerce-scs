package com.example.commerce.catalog.core.application.dto;

import com.example.commerce.catalog.core.domain.entity.product.Price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//model mapper requires args constructor
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLineItemDTO {
	
    private String sku;
    private String name;
    private int amount;
    private Price price;

}
