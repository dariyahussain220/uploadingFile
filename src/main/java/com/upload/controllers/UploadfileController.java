package com.upload.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.models.FileInformation;
import com.upload.services.FileUploadService;
import com.upload.services.ResponseUtils;

@RestController
@RequestMapping("/files")
public class UploadfileController {

    @Autowired
    private FileUploadService service;

    @Autowired
    private ResponseUtils response;

    @GetMapping
    public List<FileInformation> getAllFiles() {

        return service.getUploadedFiles();
    }
    
    @PostMapping
    public ResponseEntity<ResponseUtils> saveFile(@RequestPart MultipartFile file) {

        int rowEffected = service.saveUploadedFile(file);

        if (rowEffected > 0) {
            response.setMessage("Success");
            response.setStatus(HttpStatus.CREATED);
            return new ResponseEntity<ResponseUtils>(response, HttpStatus.CREATED);
        } else {
            response.setMessage("Failed");
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<ResponseUtils>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
