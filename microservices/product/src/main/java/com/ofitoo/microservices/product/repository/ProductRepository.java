package com.ofitoo.microservices.product.repository;

import com.ofitoo.microservices.product.model.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    List<ProductEntity> findByBarcode(String barcode);
}
