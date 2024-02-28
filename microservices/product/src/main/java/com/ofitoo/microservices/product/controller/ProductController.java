package com.ofitoo.microservices.product.controller;


import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDto;
import com.ofitoo.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody final CreateProductDto createProductDto, @RequestHeader("ownerId") Long ownerId) {
        final var product = productService.addProduct(createProductDto, ownerId);

        return ResponseEntity.ok().body(product);
    }
}
