package com.learning.datawarehouse.controllers;


import com.learning.datawarehouse.DatawarehouseApplication;
import com.learning.datawarehouse.model.InventoryEntity;
import com.learning.datawarehouse.model.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DatawarehouseApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-integration.properties")
public class InventoryControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    public void testFetchInventoryById() throws IOException {
        String serverUrl = "http://localhost:"+port+"/api/v1/inventories/6";
        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<ProductEntity> response = restTemplate.getForEntity(serverUrl,ProductEntity.class);
        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForEntity(serverUrl, InventoryEntity.class);
        });
    }

    // More test case for upload functionality, update functionality
}