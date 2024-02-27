package com.ofitoo.microservices.product.dto;

import com.ofitoo.microservices.product.enums.Visibility;

public record ProductDto(
        String id,
        String name,
        Long ownerId,
        double kcal,
        Visibility visibility,
        String barcode,
        double protein,
        double carbohydrate,
        double fat,
        double sugar,
        double fiber,
        double salt,
        String description,
        String manufacturer
) {
}
