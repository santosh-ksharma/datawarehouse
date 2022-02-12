package com.learning.datawarehouse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.datawarehouse.model.ArticleEntity;
import com.learning.datawarehouse.model.InventoryEntity;
import com.learning.datawarehouse.repositories.InventoryRepository;
import com.learning.datawarehouse.upload.*;
import com.learning.datawarehouse.util.InventoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InventoryService extends GenericService{

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryEntity> listAllInventory() {
        return inventoryRepository.findAll();
    }
    // List all inventory in stock
    // List all inventory out of stock

    public InventoryEntity fetchInventoryById(Integer id) {
        return inventoryRepository.findById(id).get();
    }

    private void throwErrIfArtNotAvail(Optional<InventoryEntity> inventoryEntityToUpdate, String reason) {
        if (!inventoryEntityToUpdate.isPresent())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, reason);
    }

    //Provision to update inventory name and stock for a given inventory id.
    public InventoryEntity updateInventory(int id, InventoryEntity inventoryEntity) {
        Optional<InventoryEntity> inventoryEntityToUpdate =inventoryRepository.findById(id);
        throwErrIfArtNotAvail(inventoryEntityToUpdate, "Article id not found");
        BeanUtils.copyProperties(inventoryEntity, inventoryEntityToUpdate.get(),"art_id");
        return inventoryRepository.saveAndFlush(inventoryEntityToUpdate.get());
    }

    public boolean areArtOfProdInStock(List<ArticleEntity> articles) {
        for(ArticleEntity articleEntity :articles){
            Optional<InventoryEntity> inventoryEntityToUpdate =inventoryRepository.findById(articleEntity.getArtId());
            throwErrIfArtNotAvail(inventoryEntityToUpdate, "Article id present in product is not available in the inventory");
            if(inventoryEntityToUpdate.get().getStock()- articleEntity.getAmountOf()<0)
                return false;
        }
        return true;
    }

    //Reduce inventory only after ensuring all articles of a product are in stock.
    public void reduceInventory(List<ArticleEntity> articleEntities) {
        for(ArticleEntity articleEntity : articleEntities) {
            InventoryEntity inventoryEntityToUpdate = inventoryRepository.findById(articleEntity.getArtId()).get();
            //Update balance stock of article in Inventory
            inventoryEntityToUpdate.setStock(inventoryEntityToUpdate.getStock()- articleEntity.getAmountOf());
            inventoryRepository.saveAndFlush(inventoryEntityToUpdate);
        }

    }

    private Inventories mapFileToBean(File inventoryInputFile) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        return mapper.readValue(inventoryInputFile, Inventories.class);
    }

    public void saveUploadedFile(MultipartFile file) throws IOException {
        File myFile = getFile(file);
        Inventories inventories = mapFileToBean(myFile);
        for(InventoryInfo inventoryInfo: inventories.getInventory()){
            InventoryEntity invEntity= InventoryMapper.INSTANCE.toInventoryEntity(inventoryInfo);
            save(invEntity);
        }
    }

    public void save(InventoryEntity inventoryEntity){
        inventoryRepository.save(inventoryEntity);
    }
}
