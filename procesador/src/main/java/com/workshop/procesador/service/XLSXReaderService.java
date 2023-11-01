package com.workshop.procesador.service;

import com.poiji.bind.Poiji;
import com.workshop.procesador.dto.FileProcessorDTO;
import com.workshop.procesador.dto.FileRequestDTO;
import com.workshop.procesador.dto.XLSXFileDTO;
import com.workshop.procesador.feign.FeignFileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class XLSXReaderService implements FileProcessorDTO {

    private final FeignFileClient feignFileClient;

    @Autowired
    public XLSXReaderService(FeignFileClient feignFileClient) {
        this.feignFileClient = feignFileClient;
    }

    @Override
    public Map<String, Integer> fileProcess(String filePath) {

        int validRows = 0;
        int invalidRows = 0;

        File file = new File(filePath);
        List<XLSXFileDTO> records = Poiji.fromExcel(file, XLSXFileDTO.class);

        Map<String, Integer> validations = new HashMap<>();

        for (XLSXFileDTO record: records) {
            String row = record.getInjuryLocation()+","+record.getReportType();
            String[] arrayRow = row.split(",");


            FileRequestDTO fileRequestDTO = new FileRequestDTO();
            fileRequestDTO.setRecords(arrayRow);
            fileRequestDTO.setType(".xlsx");

            if (feignFileClient.upload(fileRequestDTO)) {
                validRows++;
            } else {
                invalidRows++;
            }
        }

        validations.put("validRows", validRows);
        validations.put("invalidRows", invalidRows);

        return validations;
    }
}
