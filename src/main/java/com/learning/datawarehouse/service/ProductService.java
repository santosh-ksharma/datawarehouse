package com.learning.datawarehouse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.datawarehouse.model.ArticleEntity;
import com.learning.datawarehouse.model.ProductEntity;
import com.learning.datawarehouse.repositories.ArticleRepository;
import com.learning.datawarehouse.repositories.ProductRepository;
import com.learning.datawarehouse.upload.ProductInfo;
import com.learning.datawarehouse.upload.ProductMapper;
import com.learning.datawarehouse.upload.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService extends GenericService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private InventoryService inventoryService;

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
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product id not found");
    }

    public void delete (Integer productId) {
        Optional<ProductEntity> productItem=productRepository.findByProductId(productId);
        throwErrIfProdNotFound(productItem);
        ProductEntity productEntity =productItem.get();
        Set<ArticleEntity> articleEntities = productEntity.getArticleEntities();
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

    private Products mapFileToBean(File productsInputFile) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        return mapper.readValue(productsInputFile, Products.class);
    }

    public void saveUploadedFile(MultipartFile file) throws IOException {
        File myFile = getFile(file);
        Products productInfos = mapFileToBean(myFile);
        for(ProductInfo productInfo: productInfos.getProducts()){
            ProductEntity prodEntity= ProductMapper.INSTANCE.toProductModel(productInfo);
            save(prodEntity);
        }
    }

    public void save(ProductEntity productEntity){
        productRepository.save(productEntity);
        System.out.println(productEntity.getArticleEntities().size());

    }

}