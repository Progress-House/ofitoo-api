package com.ofitoo.microservices.product.integration;

import com.ofitoo.microservices.product.model.CreateProductDtoModelTestUtil;
import com.ofitoo.microservices.product.model.ProductDtoModelTestUtil;
import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDto;
import com.ofitoo.microservices.product.model.entity.ProductEntity;
import com.ofitoo.microservices.product.repository.ProductRepository;
import com.ofitoo.microservices.product.utils.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.ofitoo.microservices.product.model.enums.Visibility.PRIVATE;
import static com.ofitoo.microservices.product.model.enums.Visibility.PUBLIC;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class ProductIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateProductInDatabaseAndReturnIt() {

        // given
        final CreateProductDto createProductDto = CreateProductDtoModelTestUtil.basic();
        final ProductDto expectedProductDto = ProductDtoModelTestUtil.basic();

        // when
        final ProductDto productDto = given()
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(USER_ID, "123")
                .and()
                .body(createProductDto)
                .when()
                .post(BASE_PATH)
                .then()
                .statusCode(200)
                .extract().as(ProductDto.class);

        // then
        assertThat(productDto)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedProductDto);
    }

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldReturnCorrectNumberOfOwnedProductsByBarcode() {
        // given
        productRepository.save(ProductEntity.builder()
                .name("Product 1")
                .ownerId(123L)
                .kcal(100.0)
                .visibility(PRIVATE)
                .barcode("barcode")
                .protein(10.0)
                .carbohydrate(20.0)
                .fat(5.0)
                .sugar(8.0)
                .fiber(3.0)
                .salt(1.5)
                .description("Product description")
                .manufacturer("Manufacturer")
                .build());

        productRepository.save(ProductEntity.builder()
                .name("Product 2")
                .ownerId(123L)
                .kcal(150.0)
                .visibility(PRIVATE)
                .barcode("innybarcode")
                .protein(15.0)
                .carbohydrate(25.0)
                .fat(7.0)
                .sugar(10.0)
                .fiber(4.0)
                .salt(2.0)
                .description("Another product description")
                .manufacturer("Another manufacturer")
                .build());

        productRepository.save(ProductEntity.builder()
                .name("Product 3")
                .ownerId(123L)
                .kcal(200.0)
                .visibility(PUBLIC)
                .barcode("barcode")
                .protein(20.0)
                .carbohydrate(30.0)
                .fat(8.0)
                .sugar(12.0)
                .fiber(5.0)
                .salt(2.5)
                .description("Yet another product description")
                .manufacturer("Yet another manufacturer")
                .build());

        productRepository.save(ProductEntity.builder()
                .name("Product 4")
                .ownerId(432L)
                .kcal(250.0)
                .visibility(PRIVATE)
                .barcode("barcode")
                .protein(25.0)
                .carbohydrate(35.0)
                .fat(9.0)
                .sugar(15.0)
                .fiber(6.0)
                .salt(3.0)
                .description("Fourth product description")
                .manufacturer("Fourth manufacturer")
                .build());

        // when
        List<ProductEntity> privateProductsByBarcode = productRepository.findByBarcodeAndOwnerIdAndVisibility("barcode", 123L, PRIVATE);
        List<ProductEntity> nonExistentProducts = productRepository.findByBarcodeAndOwnerIdAndVisibility("innybarcode", 111L, PRIVATE);

        // then
        assertEquals(1, privateProductsByBarcode.size(), "Should return 1 private product with given barcode and owner ID");
        assertEquals(0, nonExistentProducts.size(), "Should not return any products with non-existent barcode and owner ID");
    }

}
