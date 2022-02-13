package com.learning.datawarehouse.service;

import com.learning.datawarehouse.exception.FileException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
@NoArgsConstructor
public class GenericService {

    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;

    protected File getFile(MultipartFile file) {
        File productsInputFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
        try {
            productsInputFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(productsInputFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            log.error("GenericService:getFile:1004",e);
           throw new FileException("Error during file operation","1004");
        }
        return productsInputFile;
    }

}
