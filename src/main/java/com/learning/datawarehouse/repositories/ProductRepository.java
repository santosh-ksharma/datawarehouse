package com.learning.datawarehouse.repositories;

import com.learning.datawarehouse.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

     Optional<ProductEntity> findByProductId(Integer productId);
     Optional<ProductEntity> findByProductName(String productName);
     List<ProductEntity> deleteByProductId(Integer productId);
}
