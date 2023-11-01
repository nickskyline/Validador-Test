package com.workshop.procesador.service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.workshop.procesador.dto.FileRequestDTO;
import com.workshop.procesador.dto.FileProcessorDTO;
import com.workshop.procesador.feign.FeignFileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class CSVReaderService implements FileProcessorDTO {
    private CSVReader reader;
    private String records[] = null;
    private FeignFileClient feignFileClient;

    @Autowired
    public CSVReaderService(FeignFileClient feignFileClient) {
        this.feignFileClient = feignFileClient;
    }

    public Map<String, Integer> fileProcess(String filePath) {
        int validRows = 0;
        int invalidRows = 0;
        Map<String, Integer> validations = new HashMap<>();

        //Este nuevo lector lector lo que hace es simplemente transformar las otras posiciones de las lineas invalidas en strings vacios ""

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withIgnoreQuotations(true) // Ignorar comillas
                        .build())
                .withSkipLines(1)
                .build())
        {

            String[] records;

            while ((records = reader.readNext()) != null) {

                FileRequestDTO fileRequestDTO = new FileRequestDTO();
                fileRequestDTO.setRecords(records);
                fileRequestDTO.setType(".csv");

                if (feignFileClient.upload(fileRequestDTO)) {
                    validRows++;
                } else {
                    invalidRows++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        validations.put("validRows", validRows);
        validations.put("invalidRows", invalidRows);
        return validations;
    }
}