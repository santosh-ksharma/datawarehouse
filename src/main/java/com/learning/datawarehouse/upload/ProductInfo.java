package com.learning.datawarehouse.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo implements Serializable {
    private Integer productId;
    private String name;
    //Keeping the same name as mentioned in product file for automatic json to bean mapping
    private List<ArticleInfo> contain_articles;

}
