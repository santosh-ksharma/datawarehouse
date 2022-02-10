package com.learning.datawarehouse.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@NoArgsConstructor
public class GenericService {

    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;

    protected File getFile(MultipartFile file) throws IOException {
        File productsInputFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
        productsInputFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(productsInputFile);
        fos.write(file.getBytes());
        fos.close();
        return productsInputFile;
    }

}
