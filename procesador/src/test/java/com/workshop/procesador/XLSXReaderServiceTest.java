package com.workshop.procesador;

import com.workshop.procesador.dto.XLSXFileDTO;
import com.workshop.procesador.feign.FeignFileClient;
import com.workshop.procesador.service.XLSXReaderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.ArrayList;
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
    public void testFileProcess() {
        File file = new File("People2.xlsx");
        List<XLSXFileDTO> records = new ArrayList<>();

        XLSXFileDTO record1 = new XLSXFileDTO();
        record1.setInjuryLocation("Location1");
        record1.setReportType("Type1");

        XLSXFileDTO record2 = new XLSXFileDTO();
        record2.setInjuryLocation("Location2");
        record2.setReportType("Type2");

        records.add(record1);
        records.add(record2);

        when(feignFileClient.upload(any())).thenReturn(true);

        Map<String, Integer> validations = xlsxReaderService.fileProcess(file.getAbsolutePath());

        assertEquals(0, (int) validations.get("validRows"));
        assertEquals(0, (int) validations.get("invalidRows"));
    }
}
