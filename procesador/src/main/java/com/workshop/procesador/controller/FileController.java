package com.workshop.procesador.controller;

import com.workshop.procesador.dto.FileDTO;
import com.workshop.procesador.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Integer>> uploadFile(@RequestBody FileDTO fileDTO) throws FileNotFoundException {
        return new ResponseEntity<>(this.fileService.uploadFile(fileDTO), HttpStatus.CREATED);
    }
}
