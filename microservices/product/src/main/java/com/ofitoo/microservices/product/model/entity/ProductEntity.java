package com.ofitoo.microservices.product.model.entity;

import com.ofitoo.microservices.product.model.enums.Visibility;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@ToString
@Document("product")
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private Long ownerId;
    private Double kcal;
    private Visibility visibility;
    private String barcode;
    private Double protein;
    private Double carbohydrate;
    private Double fat;
    private Double sugar;
    private Double fiber;
    private Double salt;
    private String description;
    private String manufacturer;
}
