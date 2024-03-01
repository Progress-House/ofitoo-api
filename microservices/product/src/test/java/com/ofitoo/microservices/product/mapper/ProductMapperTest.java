package com.ofitoo.microservices.product.mapper;

import com.ofitoo.microservices.product.model.CreateProductDtoModel;
import com.ofitoo.microservices.product.model.ProductEntityModel;
import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapper();

    @Test
    void ProductMapper$toEntityShouldMapCreateProductDtoToProductEntity() {

        // given
        final CreateProductDto createProductDto = CreateProductDtoModel.basic();
        final ProductEntity expectedProductEntity = ProductEntityModel.basic();

        // when
        final ProductEntity actualEntity = productMapper.toEntity(createProductDto, 123L);

        // then
        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedProductEntity);
    }
}
