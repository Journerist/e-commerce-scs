package com.example.commerce.catalog.core.application;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.commerce.catalog.core.application.dto.ProductDTO;
import com.example.commerce.catalog.core.domain.entity.product.Product;
import com.example.commerce.catalog.core.domain.entity.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Set<ProductDTO> getAllAvailableProducts() {
        return productRepository.findAll().stream().map(this::toProductDTO).collect(Collectors.toSet());
    }

    private ProductDTO toProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

}
