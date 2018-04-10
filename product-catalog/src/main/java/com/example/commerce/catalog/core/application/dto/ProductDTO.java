package com.example.commerce.catalog.core.application.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String sku;
    private String name;
    private PriceDTO price;
    private String productCategory;
    private boolean available;
}
