package com.example.commerce.catalog.core.application.dto;

import lombok.Value;

@Value
public final class ProductDTO {
    private final String sku;
    private final String name;
    private final PriceDTO price;
    private final String productCategory;
    private final boolean available;
}
