package com.workshop.validador.controller;
import com.workshop.validador.dto.FileRequestDTO;
import com.workshop.validador.service.RecordsValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/files")
public class RecordsValidatorController {

    @Autowired
    private RecordsValidatorService recordsValidatorService;


    @PostMapping
    public boolean validateRecords(@RequestBody FileRequestDTO data) {

        return this.recordsValidatorService.validateRecords(data);
    }
}
