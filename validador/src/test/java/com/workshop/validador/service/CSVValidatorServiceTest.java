package com.workshop.validador.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVValidatorServiceTest {

    private CSVValidatorService csvValidatorService;

    @BeforeEach
    void setUp() {
        this.csvValidatorService = new CSVValidatorService();

    }

    @Test
    void validateRecords() {
        String[] validData = {
                "1","2","3","4","5","test@test.com","6","1997-07-21","Educational psychologist"
        };

        assertTrue(csvValidatorService.validateRecords(validData));

        String[] invalidData = {
                "1","2","3","4","5","test","6","1880-01-01","afsdsfdfgfdgd"
        };
        assertFalse(csvValidatorService.validateRecords(invalidData));

    }

    @Test
    void validateMail() {
        assertTrue(csvValidatorService.validateMail("test@email.com"));
        assertFalse(csvValidatorService.validateMail("invalidEmail"));
    }

    @Test
    void validateBirthdate() {
        String fecha = "1997-07-21";
        assertTrue(csvValidatorService.validateBirthdate(fecha));
        assertFalse(csvValidatorService.validateBirthdate(""));
        assertFalse(csvValidatorService.validateBirthdate("1880-01-01"));
    }

    @Test
    void validateJobTitle() {
        assertFalse(csvValidatorService.validateJobTitle("dfdhhgdfgh"));
        assertTrue(csvValidatorService.validateJobTitle("Haematologist"));
        assertTrue(csvValidatorService.validateJobTitle("Phytotherapist"));
        assertTrue(csvValidatorService.validateJobTitle("Building surveyor"));
        assertTrue(csvValidatorService.validateJobTitle("Insurance account manager"));
        assertTrue(csvValidatorService.validateJobTitle("Educational psychologist"));

    }
}