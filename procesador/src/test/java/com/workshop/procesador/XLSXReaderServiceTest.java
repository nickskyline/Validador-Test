package com.workshop.procesador;

import com.workshop.procesador.dto.FileRequestDTO;
import com.workshop.procesador.dto.XLSXFileDTO;
import com.workshop.procesador.feign.FeignFileClient;
import com.workshop.procesador.service.XLSXReaderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class XLSXReaderServiceTest {

    @Mock
    private FeignFileClient feignFileClient;

    private XLSXReaderService xlsxReaderService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        xlsxReaderService = new XLSXReaderService(feignFileClient);
    }

    @Test
    public void testFileProcessLoop() {
        XLSXFileDTO record1 = new XLSXFileDTO();
        record1.setInjuryLocation("Location1");
        record1.setReportType("Type1");

        XLSXFileDTO record2 = new XLSXFileDTO();
        record2.setInjuryLocation("Location2");
        record2.setReportType("Type2");

        List<XLSXFileDTO> records = Arrays.asList(record1, record2);

        when(feignFileClient.upload(any())).thenReturn(true).thenReturn(false);

        int validRows = 0;
        int invalidRows = 0;

        for (XLSXFileDTO record : records) {
            String row = record.getInjuryLocation() + "," + record.getReportType();
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

        assertEquals(1, validRows);
        assertEquals(1, invalidRows);
    }
}
