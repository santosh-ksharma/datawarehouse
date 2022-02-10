package com.learning.datawarehouse.controllers;

import com.learning.datawarehouse.model.InventoryEntity;
import com.learning.datawarehouse.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryEntity> listAllInventory() {
        return inventoryService.listAllInventory();
    }

    @GetMapping(value = "{id}")
    public InventoryEntity fetchInventoryById(@PathVariable Integer id) {
        return inventoryService.fetchInventoryById(id);
    }

    @PutMapping(value = "{id}")
        public InventoryEntity updateInventory(@PathVariable int id, @RequestBody InventoryEntity inventoryEntity) {
        return inventoryService.updateInventory(id, inventoryEntity);
    }

    @PostMapping(value="upload")
    public ResponseEntity<Object> upload(@RequestPart("file") MultipartFile file) {
        try {
            inventoryService.saveUploadedFile(file);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error while uploading inventory file. Please retry after sometime.");
        }
        return new ResponseEntity<Object>("The file uploaded successfully", HttpStatus.CREATED);
    }
}
