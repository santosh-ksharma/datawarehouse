package com.learning.datawarehouse.controllers;

import com.learning.datawarehouse.model.ProductEntity;
import com.learning.datawarehouse.service.ProductService;

import com.learning.datawarehouse.dto.ProductInfo;
import com.learning.datawarehouse.util.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductInfo>> fetch() {
        return ResponseEntity.ok().body(productService.fetchAllProducts().stream().map(productEntity -> ProductMapper.toProductDTO(productEntity)).collect(Collectors.toList()));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProductInfo> fetchSingleProduct(@PathVariable Integer id) {
        return ResponseEntity.ok().body(ProductMapper.toProductDTO(productService.fetchSingleProduct(id)));
    }

    @Transactional
    @DeleteMapping(value = "{id}")
    public void delete (@PathVariable Integer id) {
        productService.delete(id);
    }

    @PostMapping(value="upload")
    //Check if file is empty and throw exception
    public ResponseEntity<Object> upload(@RequestPart("file") MultipartFile file) {
        try {
            productService.saveUploadedFile(file);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error while product file. Please retry after sometime.");
        }
        return new ResponseEntity<Object>("The file uploaded successfully", HttpStatus.CREATED);
    }

}