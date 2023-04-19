package com.example.restwithspringbootandjavaerudio.controller;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.UploadFileVOResponse;
import com.example.restwithspringbootandjavaerudio.service.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "File endpoint")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/file/v1")
public class FileController {

    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public UploadFileVOResponse uploadFile(@RequestParam("file")MultipartFile multipartFile) {
        log.info("Storing file to disk...");
        String filename = fileStorageService.storeFile(multipartFile);
        String fileDownloadUri = ServletUriComponentsBuilder.
                fromCurrentContextPath().path("/api/file/v1/download/")
                .path(filename)
                .toUriString();

        return UploadFileVOResponse.builder()
                .filename(filename)
                .fileDownloadUri(fileDownloadUri)
                .filetype(multipartFile.getContentType())
                .size(multipartFile.getSize())
                .build();
    }

    @PostMapping("/upload-multiple-file")
    public List<UploadFileVOResponse> uploadMultipleFile(@RequestParam("files")MultipartFile[] multipartFileList) {
        log.info("Storing files to disk...");

        return Arrays.stream(multipartFileList)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }


    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request) {
        log.info("reading a file on disk...");

        Resource resource = fileStorageService.loadFileAsResource(filename);

        String contentType = "";

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception e){
            log.info("Could not determine file type");
        }

        if(contentType.isBlank()) contentType = "application/octet-stream";


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
