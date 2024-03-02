package com.ofitoo.microservices.product.mapper;

import com.ofitoo.microservices.product.model.CreateProductDtoModel;
import com.ofitoo.microservices.product.model.ProductDtoModel;
import com.ofitoo.microservices.product.model.ProductEntityModel;
import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDto;
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

    @Test
    void ProductMapper$toDtoShouldMapProductEntityToProductDto() {

        // given
        final ProductEntity productEntity = ProductEntityModel.basic();
        final ProductDto expectdProductDto = ProductDtoModel.basic();

        // when
        final ProductDto actualProductDto = productMapper.toDto(productEntity);

        // then
        assertThat(actualProductDto)
                .usingRecursiveComparison()
                .isEqualTo(expectdProductDto);
    }
}
