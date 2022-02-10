package com.learning.datawarehouse.repositories;

import com.learning.datawarehouse.model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {
}
