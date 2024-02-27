package com.ofitoo.microservices.product.dto;

import com.ofitoo.microservices.product.enums.Visibility;

public record CreateProductDto(
        String name,
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
