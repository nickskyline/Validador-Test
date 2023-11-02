package com.workshop.procesador.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.workshop.procesador.feign.FeignFileClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

class CSVReaderServiceTest {

    @InjectMocks
    private CSVReaderService csvReaderService;

    @Mock
    private FeignFileClient feignFileClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFileProcessConLineasValidas() {
        // Arrange
        Mockito.when(feignFileClient.upload(Mockito.any())).thenReturn(true);
        String ruta = "/home/nicolas/IdeaProjects/Validador Test/procesador/src/main/resources/people.csv";

        // Act
        Map<String, Integer> result = csvReaderService.fileProcess(ruta);

        // Assert
        assertEquals(10000, result.get("validRows"));
        assertEquals(0, result.get("invalidRows"));
    }

    @Test
    public void testFileProcessConLineasInvalidas() {
        // Arrange
        Mockito.when(feignFileClient.upload(Mockito.any())).thenReturn(false);
        String ruta = "/home/nicolas/IdeaProjects/Validador Test/procesador/src/main/resources/people.csv";

        // Act
        Map<String, Integer> result = csvReaderService.fileProcess(ruta);

        // Assert
        assertEquals(0, result.get("validRows"));
        assertEquals(10000, result.get("invalidRows"));
    }

    @Test
    public void testFileProcessConIOException() {
        // Arrange
        Mockito.when(feignFileClient.upload(Mockito.any())).thenReturn(true);
        // Simular una IOException al abrir el archivo
        String invalidFilePath = "invalid_file.csv";
        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            this.csvReaderService.fileProcess(invalidFilePath);
        });
    }
}