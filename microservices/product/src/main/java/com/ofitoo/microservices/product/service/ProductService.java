package com.ofitoo.microservices.product.service;

import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDto;
import com.ofitoo.microservices.product.mapper.ProductMapper;
import com.ofitoo.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto addProduct(final CreateProductDto createProductDto, final Long ownerId) {
        final var product = productMapper.toEntity(createProductDto, ownerId);
        final var savedEntity = productRepository.insert(product);

        return productMapper.toDto(savedEntity);
    }
}
