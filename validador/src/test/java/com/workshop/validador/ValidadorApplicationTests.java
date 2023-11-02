package com.workshop.validador;

import com.workshop.validador.service.CSVValidatorService;
import com.workshop.validador.service.XLSXValidatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ValidadorApplicationTests {

	@Test
	void contextLoads() {
	}


	// Test xlsxValidator
	@Autowired
	private XLSXValidatorService xlsxValidatorService;

	@Test
	public void testValidateRecords(){
		String[] validData = {"Location 1", "Near Miss"};
		assertTrue(xlsxValidatorService.validateRecords(validData));

		String[] invalidData = {"N/A","other"};
		assertFalse(xlsxValidatorService.validateRecords(invalidData));
	}

	@Test
	public void testIsValidInInjuryLocation(){
		assertTrue(xlsxValidatorService.isValidInjuryLocation("Location  1"));
		assertFalse(xlsxValidatorService.isValidInjuryLocation("N/A"));
	}

	@Test
	public void	testIsValidReportType() {
		assertTrue(xlsxValidatorService.isValidReportType("Near Miss"));
		assertTrue(xlsxValidatorService.isValidReportType("Lost Time"));
		assertTrue(xlsxValidatorService.isValidReportType("First Aid"));
		assertFalse(xlsxValidatorService.isValidReportType("Other"));
	}

	//test csvValidatorService
	@Autowired
	private CSVValidatorService csvValidatorService;
	@Test
	public void testValidateEmail() {
		assertTrue(csvValidatorService.validateMail("test@email.com"));
		assertFalse(csvValidatorService.validateMail("invalidEmail"));
	}



}
