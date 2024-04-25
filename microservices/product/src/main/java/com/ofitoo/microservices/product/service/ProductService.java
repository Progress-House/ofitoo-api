package com.ofitoo.microservices.product.service;

import com.ofitoo.microservices.product.mapper.ProductMapper;
import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDto;
import com.ofitoo.microservices.product.model.entity.ProductEntity;
import com.ofitoo.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto createProduct(final CreateProductDto createProductDto, final Long userId) {
        final ProductEntity product = productMapper.toEntity(createProductDto, userId);
        final ProductEntity savedEntity = productRepository.insert(product);

        return productMapper.toDto(savedEntity);
    }

    public List<ProductDto> getOwnedProductsByBarcode(final String barcode, final Long userId) {
        List<ProductEntity> products = productRepository.findByBarcodeAndOwnerId(barcode, userId);
        List<ProductEntity> filteredProducts = products.stream()
                .sorted(Comparator.comparing(ProductEntity::getVisibility).reversed())
                .toList();

        return filteredProducts.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());

    }
}
