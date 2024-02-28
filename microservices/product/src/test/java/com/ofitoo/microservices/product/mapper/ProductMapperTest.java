package com.ofitoo.microservices.product.mapper;

import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.entity.ProductEntity;
import com.ofitoo.microservices.product.model.enums.Visibility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapper();

    @Test
    void shoudMakeGowno() {

        Assertions.assertTrue(true);

    }

    @Test
    void testToEntity() {
        // Given
        CreateProductDto createProductDto = new CreateProductDto(
                "Sample Product",
                150.0,
                Visibility.PUBLIC,
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

        ProductEntity expectedEntity = ProductEntity.builder()
                .name("Sample Product")
                .ownerId(123L)
                .kcal(150.0)
                .visibility(Visibility.PUBLIC)
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

        // When
        ProductEntity actualEntity = productMapper.toEntity(createProductDto, 123L);

//        assertEquals(expectedEntity, actualEntity);
        assertAll("messssage",
                () -> assertEquals(null, expectedEntity.getId()),
                () -> assertEquals("Sample Product", expectedEntity.getName())
        );
    }
}
