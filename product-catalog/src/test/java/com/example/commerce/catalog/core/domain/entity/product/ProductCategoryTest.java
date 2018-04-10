package com.example.commerce.catalog.core.domain.entity.product;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ProductCategoryTest {

    @Test
    public void contract() throws Exception {
        EqualsVerifier.forClass(ProductCategory.class)
        .verify();
    }
    
}
