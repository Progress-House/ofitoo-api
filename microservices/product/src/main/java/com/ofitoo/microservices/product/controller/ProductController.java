package com.ofitoo.microservices.product.controller;

import com.ofitoo.microservices.product.dto.CreateProductDto;
import com.ofitoo.microservices.product.dto.ProductDto;
import com.ofitoo.microservices.product.enums.Visibility;
import com.ofitoo.microservices.product.service.ProductService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody final CreateProductDto createProductDto) {
        final Long ownerId = 12L; //todo establish form where get it
        final var product = productService.addProduct(createProductDto, ownerId);

        return ResponseEntity.ok().body(product);
    }
}
