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
    private String name;
    private List<ArticleInfo> contain_articles;

}
