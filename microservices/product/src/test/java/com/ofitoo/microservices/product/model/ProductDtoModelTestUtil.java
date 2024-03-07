package com.ofitoo.microservices.product.model;

import com.ofitoo.microservices.product.model.dto.ProductDto;

import static com.ofitoo.microservices.product.model.enums.Visibility.PUBLIC;

abstract public class ProductDtoModelTestUtil {

    public static ProductDto basic() {
        return new ProductDto(
                null,
                "Sample Product",
                123L,
                150.0,
                PUBLIC,
                "123456789",
                10.0,
                20.0,
                5.0,
                15.0,
                2.0,
                0.5,
                "Sample product description",
                "Sample Manufacturer"
        );
    }
}
