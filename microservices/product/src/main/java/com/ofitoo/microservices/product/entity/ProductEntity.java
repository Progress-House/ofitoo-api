package com.ofitoo.microservices.product.entity;

import com.ofitoo.microservices.product.enums.Visibility;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document("product")
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private Long ownerId;
    private double kcal;
    private Visibility visibility;
    private String barcode;
    private double protein;
    private double carbohydrate;
    private double fat;
    private double sugar;
    private double fiber;
    private double salt;
    private String description;
    private String manufacturer;
}
