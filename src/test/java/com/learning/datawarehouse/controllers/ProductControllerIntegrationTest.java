package com.learning.datawarehouse.controllers;


import com.learning.datawarehouse.DatawarehouseApplication;
import com.learning.datawarehouse.dto.ProductInfo;
import com.learning.datawarehouse.model.ProductEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import java.io.IOException;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DatawarehouseApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-integration.properties")
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    public void testFetchSingleProduct() throws IOException {
        String serverUrl = "http://localhost:"+port+"/api/v1/products/1";
        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<ProductEntity> response = restTemplate.getForEntity(serverUrl,ProductEntity.class);
        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForEntity(serverUrl,ProductEntity.class);
        });
    }

}