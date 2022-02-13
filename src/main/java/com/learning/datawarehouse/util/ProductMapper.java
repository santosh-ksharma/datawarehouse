package com.learning.datawarehouse.util;

import com.learning.datawarehouse.model.ArticleEntity;
import com.learning.datawarehouse.model.ProductEntity;
import com.learning.datawarehouse.dto.ArticleInfo;
import com.learning.datawarehouse.dto.ProductInfo;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity toProductEntity(ProductInfo productInfo) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(productInfo.getName());
        List<ArticleEntity> articleEntities=productInfo.getContain_articles().stream().map(articleInfo -> {
           ArticleEntity articleEntity=ArticleMapper.INSTANCE.toArticleEntity(articleInfo);
           articleEntity.setProductEntity(productEntity);
           return articleEntity;
        }).collect(Collectors.toList());
        productEntity.setArticleEntities(articleEntities);
        return productEntity;
        }

    public static ProductInfo toProductDTO(ProductEntity productEntity) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setName(productEntity.getProductName());
        productInfo.setProductId(productEntity.getProductId());
        List<ArticleInfo> articleInfos=productEntity.getArticleEntities().stream().map(articleEntity -> ArticleMapper.INSTANCE.toArticleDTO(articleEntity)).collect(Collectors.toList());
        productInfo.setContain_articles(articleInfos);
        return productInfo;
        }
}
