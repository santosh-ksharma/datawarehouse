package com.learning.datawarehouse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.datawarehouse.exception.FileIncorrectFormatException;
import com.learning.datawarehouse.exception.IntegrityViolationException;
import com.learning.datawarehouse.exception.ProductNotFoundException;
import com.learning.datawarehouse.model.ArticleEntity;
import com.learning.datawarehouse.model.ProductEntity;
import com.learning.datawarehouse.repositories.ProductRepository;
import com.learning.datawarehouse.dto.ProductInfo;
import com.learning.datawarehouse.util.ProductMapper;
import com.learning.datawarehouse.dto.Products;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService extends GenericService {

    private final ProductRepository productRepository;

    private final InventoryService inventoryService;

    public List<ProductEntity> fetchAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity fetchSingleProduct(Integer product) {
        Optional<ProductEntity> productItem=productRepository.findByProductId(product);
        throwErrIfProdNotFound(productItem);
        return productItem.get();
    }

    private void throwErrIfProdNotFound(Optional<ProductEntity> productItem) {
        if (!productItem.isPresent())
            throw new ProductNotFoundException("Product id not found","1001");
    }

    public void delete (Integer productId) {
        Optional<ProductEntity> productItem=productRepository.findByProductId(productId);
        throwErrIfProdNotFound(productItem);
        ProductEntity productEntity =productItem.get();
        List<ArticleEntity> articleEntities = productEntity.getArticleEntities();
        //Ensure each article of product is in stock and only then sell/remove the product
        if(inventoryService.areArtOfProdInStock(articleEntities)){
            inventoryService.reduceInventory(articleEntities);
            productRepository.deleteByProductId(productId);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Out of stock");
        }
    }

    public void deleteAll () {
        productRepository.deleteAll();
    }

    private Products mapFileToBean(File productsInputFile){
        ObjectMapper mapper=new ObjectMapper();
        try {
            return mapper.readValue(productsInputFile, Products.class);
        } catch (IOException e) {
            log.error("ProductService:mapFileToBean:1003",e);
           throw new FileIncorrectFormatException("Wrong file format uploaded","1003");
        }
    }

    public void saveUploadedFile(MultipartFile file) {
        File myFile = getFile(file);
        Products productInfos = mapFileToBean(myFile);
        for(ProductInfo productInfo: productInfos.getProducts()){
            ProductEntity prodEntity= ProductMapper.toProductEntity(productInfo);
            save(prodEntity);
        }
    }

    public void save(ProductEntity productEntity){
        try {
            productRepository.save(productEntity);
        }catch(Exception ex){
            if(ex instanceof DataIntegrityViolationException){
                log.error("ProductService:save:1006",ex);
                throw new IntegrityViolationException("One or more product names already exists in database. Please delete such products and upload again","1006");
            }

        }
    }

}