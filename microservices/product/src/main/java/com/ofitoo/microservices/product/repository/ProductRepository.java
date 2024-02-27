package com.ofitoo.microservices.product.repository;

import com.ofitoo.microservices.product.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

}
