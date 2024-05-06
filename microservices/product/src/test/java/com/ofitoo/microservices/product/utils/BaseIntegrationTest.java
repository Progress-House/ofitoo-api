package com.ofitoo.microservices.product.utils;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@Testcontainers
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class BaseIntegrationTest {

    protected final String CONTENT_TYPE = "Content-type";
    protected final String USER_ID = "userId";
    protected final String BARCODE = "barcode";
    protected final String BASE_PATH = "/api/v1/product";


    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.13");

    static {
        mongoDBContainer.start();
    }
}
