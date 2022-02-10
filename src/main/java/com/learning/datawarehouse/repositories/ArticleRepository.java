package com.learning.datawarehouse.repositories;

import com.learning.datawarehouse.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

}
