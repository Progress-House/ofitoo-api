package com.ofitoo.microservices.product.mapper;

import com.ofitoo.microservices.product.model.CreateProductDtoModelTestUtil;
import com.ofitoo.microservices.product.model.ProductDtoModelTestUtil;
import com.ofitoo.microservices.product.model.ProductEntityModelTestUtil;
import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDto;
import com.ofitoo.microservices.product.model.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapper();

    @Test
    void shouldMapCreateProductDtoToProductEntity() {

        // given
        final CreateProductDto createProductDto = CreateProductDtoModelTestUtil.basic();
        final ProductEntity expectedProductEntity = ProductEntityModelTestUtil.basic();

        // when
        final ProductEntity actualEntity = productMapper.toEntity(createProductDto, 123L);

        // then
        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedProductEntity);
    }

    @Test
    void shouldMapProductEntityToProductDto() {

        // given
        final ProductEntity productEntity = ProductEntityModelTestUtil.basic();
        final ProductDto expectdProductDto = ProductDtoModelTestUtil.basic();

        // when
        final ProductDto actualProductDto = productMapper.toDto(productEntity);

        // then
        assertThat(actualProductDto)
                .usingRecursiveComparison()
                .isEqualTo(expectdProductDto);
    }
}
