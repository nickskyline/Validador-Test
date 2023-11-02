package com.workshop.procesador.service;

import com.workshop.procesador.component.FileProcessorStrategyFactory;
import com.workshop.procesador.dto.FileDTO;
import com.workshop.procesador.dto.FileProcessorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FileServiceTest {

    @InjectMocks
    private FileService fileService;

    @Mock
    private FileProcessorStrategyFactory fileProcessorStrategyFactory;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUploadFile() throws FileNotFoundException {
        // Arrange
        String fileType = ".csv";
        String path = "/path/to/your/file.csv";
        FileDTO fileDTO = new FileDTO(path, fileType);

        FileProcessorDTO fileProcessorDTO = new ConcreteFileProcessorDTO();

        when(fileProcessorStrategyFactory.getStrategy(fileType)).thenReturn(fileProcessorDTO);

        // Act
        Map<String, Integer> result = fileService.uploadFile(fileDTO);

        // Assert
        assertEquals(1, result.size());
    }

    private class ConcreteFileProcessorDTO implements FileProcessorDTO {
        @Override
        public Map<String, Integer> fileProcess(String path) {
            Map<String, Integer> result = new HashMap<>();
            result.put("ejemplo", 42);
            return result;
        }
    }
}