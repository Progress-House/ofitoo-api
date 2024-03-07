package com.ofitoo.microservices.product.model.dto;

import com.ofitoo.microservices.product.model.enums.Visibility;
import lombok.With;

@With
public record ProductDto(
        String id,
        String name,
        Long ownerId,
        Double kcal,
        Visibility visibility,
        String barcode,
        Double protein,
        Double carbohydrate,
        Double fat,
        Double sugar,
        Double fiber,
        Double salt,
        String description,
        String manufacturer
) {
}
