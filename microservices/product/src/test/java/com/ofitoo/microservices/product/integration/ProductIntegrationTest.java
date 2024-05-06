package com.ofitoo.microservices.product.integration;

import com.ofitoo.microservices.product.model.CreateProductDtoModelTestUtil;
import com.ofitoo.microservices.product.model.ProductDtoModelTestUtil;
import com.ofitoo.microservices.product.model.dto.CreateProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDto;
import com.ofitoo.microservices.product.model.dto.ProductDtoList;
import com.ofitoo.microservices.product.repository.ProductRepository;
import com.ofitoo.microservices.product.utils.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.ofitoo.microservices.product.model.ProductEntityModelTestUtil.getEntityByBarcodeAndOwnerIdAndVisibility;
import static com.ofitoo.microservices.product.model.enums.Visibility.PRIVATE;
import static com.ofitoo.microservices.product.model.enums.Visibility.PUBLIC;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class ProductIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private ProductRepository productRepository;

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
    public void shouldReturnCorrectNumberOfOwnedProductsByBarcode() {
        // given
        var barcode = "exampleBarcode";
        productRepository.save(getEntityByBarcodeAndOwnerIdAndVisibility(barcode, 123L, PRIVATE));
        productRepository.save(getEntityByBarcodeAndOwnerIdAndVisibility(barcode, 123L, PUBLIC));
        productRepository.save(getEntityByBarcodeAndOwnerIdAndVisibility(barcode, 432L, PUBLIC));
        productRepository.save(getEntityByBarcodeAndOwnerIdAndVisibility("anotherBarcode", 123L, PRIVATE));

        // when
        final ProductDto expectedProductDto = ProductDtoModelTestUtil.basic()
                .withBarcode(barcode)
                .withOwnerId(123L)
                .withVisibility(PRIVATE);
        final ProductDtoList expectedProductDtoList = new ProductDtoList(List.of(expectedProductDto));

        // when
        final ProductDtoList productDtoList = given()
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(USER_ID, "123")
                .queryParam(BARCODE, barcode)
                .when()
                .get(BASE_PATH)
                .then()
                .statusCode(200)
                .extract().as(ProductDtoList.class);

        // then
        assertThat(productDtoList)
                .usingRecursiveComparison()
                .ignoringFields("productDtos.id")
                .isEqualTo(expectedProductDtoList);
    }
}
