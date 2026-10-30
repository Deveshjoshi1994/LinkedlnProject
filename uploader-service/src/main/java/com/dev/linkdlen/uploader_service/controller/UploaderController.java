package com.dev.linkdlen.uploader_service.controller;

import com.dev.linkdlen.uploader_service.service.UploaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class UploaderController {

    private final UploaderService uploaderService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) {

        String url = null;
        try {
            url = uploaderService.upload(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(url);

    }
}
