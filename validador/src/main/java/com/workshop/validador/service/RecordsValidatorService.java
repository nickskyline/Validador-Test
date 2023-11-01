package com.workshop.validador.service;

import com.workshop.validador.dto.FileRequestDTO;
import com.workshop.validador.dto.RecordValidatorDTO;
import com.workshop.validador.factory.FileValidatorStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordsValidatorService {

    private final FileValidatorStrategyFactory fileValidatorStrategyFactory;
    public boolean validateRecords(FileRequestDTO data) {

        String fileType = data.getType();
        RecordValidatorDTO strategy = fileValidatorStrategyFactory.getStrategy(fileType);

        String[] row = data.getRecords();
        return strategy.validateRecords(row);
    }

}
