package com.workshop.validador.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XLSXValidatorServiceTest {

    private XLSXValidatorService xlsxValidatorService;

    @BeforeEach
    void setUp() {
        this.xlsxValidatorService = new XLSXValidatorService();
    }

    @Test
    void validateRecords() {
        String[] validData = {"Location 1", "Near Miss"};
        assertTrue(xlsxValidatorService.validateRecords(validData));

        String[] invalidData = {"N/A","other"};
        assertFalse(xlsxValidatorService.validateRecords(invalidData));
    }

    @Test
    void isValidInjuryLocation() {
        assertTrue(xlsxValidatorService.isValidInjuryLocation("Location  1"));
        assertFalse(xlsxValidatorService.isValidInjuryLocation("N/A"));
    }

    @Test
    void isValidReportType() {
        assertTrue(xlsxValidatorService.isValidReportType("Near Miss"));
        assertTrue(xlsxValidatorService.isValidReportType("Lost Time"));
        assertTrue(xlsxValidatorService.isValidReportType("First Aid"));
        assertFalse(xlsxValidatorService.isValidReportType("Other"));
    }
}