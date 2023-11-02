package com.workshop.validador.service;

import com.workshop.validador.dto.FileRequestDTO;
import com.workshop.validador.dto.RecordValidatorDTO;
import com.workshop.validador.factory.FileValidatorStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecordsValidatorServiceTest {
    private RecordsValidatorService recordsValidatorService;
    private FileValidatorStrategyFactory fileValidatorStrategyFactory;

    @BeforeEach
    public void setUp() {
        this.fileValidatorStrategyFactory = mock(FileValidatorStrategyFactory.class);
        recordsValidatorService = new RecordsValidatorService(fileValidatorStrategyFactory);
    }

    @Test
    public void testValidateRecords() {

        //Arrange
        String fileType = "csv";
        RecordValidatorDTO mockStrategy = mock(RecordValidatorDTO.class);
        when(fileValidatorStrategyFactory.getStrategy(fileType)).thenReturn(mockStrategy);

        // Act
        FileRequestDTO data = new FileRequestDTO();
        data.setType(fileType);
        data.setRecords(new String[]{"record1", "record2"});

        when(mockStrategy.validateRecords(data.getRecords())).thenReturn(true); // Replace with the expected behavior

        boolean result = recordsValidatorService.validateRecords(data);

        // Assert
        assertTrue(result);
    }

}