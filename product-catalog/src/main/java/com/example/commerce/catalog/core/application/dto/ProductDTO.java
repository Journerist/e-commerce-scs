package com.example.commerce.catalog.core.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// model mapper requires args constructor
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProductDTO {
    private String sku;
    private String name;
    private PriceDTO price;
    private String productCategory;
    private boolean available;
}
