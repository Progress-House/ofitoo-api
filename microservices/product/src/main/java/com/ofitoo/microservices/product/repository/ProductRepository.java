package com.ofitoo.microservices.product.repository;

import com.ofitoo.microservices.product.model.entity.ProductEntity;
import com.ofitoo.microservices.product.model.enums.Visibility;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    List<ProductEntity> findByBarcodeAndOwnerIdAndVisibility(String barcode, Long ownerId, Visibility Private);
}
