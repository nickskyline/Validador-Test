package com.workshop.validador.factory;

import com.workshop.validador.dto.RecordValidatorDTO;
import com.workshop.validador.service.CSVValidatorService;
import com.workshop.validador.service.XLSXValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FileValidatorStrategyFactory {

    private CSVValidatorService csvValidatorService;
    private XLSXValidatorService xlsxValidatorService;
    private Map<String, RecordValidatorDTO> strategies;

    @Autowired
    public FileValidatorStrategyFactory(CSVValidatorService csvValidatorService, XLSXValidatorService xlsxValidatorService) {
        this.csvValidatorService = csvValidatorService;
        this.xlsxValidatorService = xlsxValidatorService;
        this.strategies = new HashMap<>(){{
            put(".csv", csvValidatorService);
            put(".xlsx", xlsxValidatorService);
        }};
    }

    public RecordValidatorDTO getStrategy(String fileType){
        return strategies.get(fileType);
    }
}
