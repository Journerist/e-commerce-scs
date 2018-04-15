package com.example.commerce.catalog.core.application.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class BasketDTO {
	
    private String id;
    private List<ProductLineItemDTO> productLineItems = new ArrayList<>();
    private String customerId;
    private String currency;
    
}
