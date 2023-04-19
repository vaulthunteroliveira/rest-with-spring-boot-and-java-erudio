package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.config.FileStorageConfig;
import com.example.restwithspringbootandjavaerudio.exceptions.FileStorageException;
import com.example.restwithspringbootandjavaerudio.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageLocation) {
        Path path = Paths.get(fileStorageLocation.getUploadDir())
                .toAbsolutePath().normalize();

        this.fileStorageLocation = path;

        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new FileStorageException("could not create directory " +
                    "where the upload files will be stored", e);
        }
    }


    public String storeFile(MultipartFile multipartFile){
        String filename = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try{
            Files.createDirectories(this.fileStorageLocation);

            filename = filename.replaceAll("(?<=\\\\.)\\\\.(?!\\\\w)|\\\\.{2,}", "_");

//            if (!filename.matches("^[a-zA-Z0-9\\s\\-\\.]+\\.(jpeg|png|mp4)$")) {
            if (!filename.matches("^.+(jpeg|png|mp4)$")) {
                throw new FileStorageException("invalid format!");
            }

            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch (Exception e){
            throw new FileStorageException("could not store file " + filename +
                    ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String filename){
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();

            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
               return resource;
            else
               throw new MyFileNotFoundException("File not found!");

        }catch (Exception e){
            throw new MyFileNotFoundException("File " + filename + " not found.", e);
        }
    }
}
