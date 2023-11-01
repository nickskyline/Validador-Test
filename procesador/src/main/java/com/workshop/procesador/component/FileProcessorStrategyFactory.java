package com.workshop.procesador.component;
import com.workshop.procesador.dto.FileProcessorDTO;
import com.workshop.procesador.service.CSVReaderService;
import com.workshop.procesador.service.XLSXReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FileProcessorStrategyFactory {

    private CSVReaderService csvReaderService;
    private XLSXReaderService xlsxReaderService;

    private Map<String, FileProcessorDTO> strategies;

    @Autowired
    public FileProcessorStrategyFactory(CSVReaderService csvReaderService, XLSXReaderService xlsxReaderService) {
        this.csvReaderService = csvReaderService;
        this.xlsxReaderService = xlsxReaderService;
        strategies = new HashMap<>(){{
            put(".csv", csvReaderService);
            put(".xlsx", xlsxReaderService);
        }};
    }

    public FileProcessorDTO getStrategy(String fileType) {
            return strategies.get(fileType);
        }
    }

