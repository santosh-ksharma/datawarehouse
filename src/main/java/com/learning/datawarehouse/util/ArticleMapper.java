package com.learning.datawarehouse.util;
import com.learning.datawarehouse.model.ArticleEntity;
import com.learning.datawarehouse.dto.ArticleInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    public static ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    @Mapping(source = "art_id", target = "artId")
    @Mapping(source = "amount_of", target = "amountOf")
    ArticleEntity toArticleEntity(ArticleInfo articleInfo);

    @Mapping(source = "artId", target = "art_id")
    @Mapping(source = "amountOf", target = "amount_of")
    ArticleInfo toArticleDTO(ArticleEntity inventoryEntity);

}

