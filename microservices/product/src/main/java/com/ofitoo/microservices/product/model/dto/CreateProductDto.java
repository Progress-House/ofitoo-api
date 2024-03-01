package com.ofitoo.microservices.product.model.dto;

import com.ofitoo.microservices.product.model.enums.Visibility;
import jakarta.validation.constraints.NotNull;
import lombok.With;

@With
public record CreateProductDto(
        String name,
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
