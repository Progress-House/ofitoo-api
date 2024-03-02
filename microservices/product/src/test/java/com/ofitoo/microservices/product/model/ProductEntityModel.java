package com.ofitoo.microservices.product.model;

import com.ofitoo.microservices.product.model.entity.ProductEntity;

import static com.ofitoo.microservices.product.model.enums.Visibility.PUBLIC;

abstract public class ProductEntityModel {

    public static ProductEntity basic() {
        return ProductEntity.builder()
                .id(null)
                .name("Sample Product")
                .ownerId(123L)
                .kcal(150.0)
                .visibility(PUBLIC)
                .barcode("123456789")
                .protein(10.0)
                .carbohydrate(20.0)
                .fat(5.0)
                .sugar(15.0)
                .fiber(2.0)
                .salt(0.5)
                .description("Sample product description")
                .manufacturer("Sample Manufacturer")
                .build();
    }
}
