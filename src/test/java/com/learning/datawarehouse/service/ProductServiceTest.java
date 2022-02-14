package com.learning.datawarehouse.service;

import com.learning.datawarehouse.exception.ProductNotFoundException;
import com.learning.datawarehouse.model.ProductEntity;
import com.learning.datawarehouse.repositories.ArticleRepository;
import com.learning.datawarehouse.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceTest {


    @Mock
    ProductRepository productRepository;

    @Mock
    ArticleRepository articleRepository;

    @Mock
    InventoryService inventoryService;

    @InjectMocks
    ProductService productService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void fetchSingleProduct() throws Exception{
        Mockito.when(productRepository.findByProductId(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productService.fetchSingleProduct(1);
        });
    }
}