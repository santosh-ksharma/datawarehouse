package com.learning.datawarehouse.upload;

import com.learning.datawarehouse.model.ArticleEntity;
import com.learning.datawarehouse.model.ProductEntity;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

public class ProductMapper {
    public static ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    public ProductEntity toProductModel(ProductInfo uploadedProductData) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setProductName(uploadedProductData.getName());
        Set<ArticleEntity> articleEntities =new HashSet<ArticleEntity>();

        for(ArticleInfo articleInfo:uploadedProductData.getContain_articles()){
            ArticleEntity articleEntity = new ArticleEntity();
            articleEntity.setAmountOf(articleInfo.getAmount_of());
            articleEntity.setArtId(articleInfo.getArt_id());
            articleEntity.setProductEntity(productEntity);
            articleEntities.add(articleEntity);
        }
        productEntity.setArticleEntities(articleEntities);
        return productEntity;
    }

}