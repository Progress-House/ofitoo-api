package com.ofitoo.microservices.product.integration;

import com.ofitoo.microservices.product.model.CreateProductDtoModelTestUtil;
import com.ofitoo.microservices.product.model.ProductDtoModelTestUtil;
import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDto;
import com.ofitoo.microservices.product.utils.BaseIntegrationTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    public void shouldReturnOwnedProductsByBarcode() {

        // given
        final CreateProductDto createProductDto = CreateProductDtoModelTestUtil.basic();

        given()
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(USER_ID, "123")
                .body(createProductDto)
                .when()
                .post(BASE_PATH)
                .then()
                .statusCode(200);

        // when
        final List<ProductDto> productDtos = given()
                .header(USER_ID, "123")
                .param("barcode", "123456789")
                .when()
                .get(BASE_PATH)
                .then()
                .statusCode(200)
                .extract().jsonPath().getList(".", ProductDto.class);
        // then
        assertThat(productDtos).isNotEmpty();
        for (ProductDto productDto : productDtos) {
            assertThat(productDto.barcode()).isEqualTo("123456789");
        }
    }

    @Test
    public void shouldReturnEmptyListWhenBarcodeNotFound() {
        // given
        // when
        final List<ProductDto> productDtos = given()
                .header(USER_ID, "123")
                .param("barcode", "987654321")
                .when()
                .get(BASE_PATH)
                .then()
                .statusCode(200)
                .extract().jsonPath().getList(".", ProductDto.class);

        // then
        assertThat(productDtos).isEmpty();
    }
}
