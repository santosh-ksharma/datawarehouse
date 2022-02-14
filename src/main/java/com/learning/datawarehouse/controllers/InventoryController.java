package com.learning.datawarehouse.controllers;

import com.learning.datawarehouse.service.InventoryService;
import com.learning.datawarehouse.dto.InventoryInfo;
import com.learning.datawarehouse.util.InventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryInfo>> listAllInventory() {
        log.info("Listing all inventory");
        return ResponseEntity.ok().
                body(inventoryService.listAllInventory().stream().
                map(inventoryEntity -> InventoryMapper.INSTANCE.toInventoryDTO(inventoryEntity)).
                collect(Collectors.toList()));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<InventoryInfo> fetchInventoryById(@PathVariable Integer id) {
        return ResponseEntity.ok().
                body(InventoryMapper.INSTANCE.toInventoryDTO(inventoryService.fetchInventoryById(id)));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<InventoryInfo> updateInventory(@PathVariable int id, @Valid @RequestBody InventoryInfo inventoryInfo) {
        return ResponseEntity.ok().
                body(inventoryService.updateInventory(id, InventoryMapper.INSTANCE.toInventoryEntity(inventoryInfo)));
    }

    @Transactional
    @DeleteMapping
    public void deleteAll (){
        inventoryService.deleteAll();
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
