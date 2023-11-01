package com.workshop.validador.service;

import com.workshop.validador.dto.FileRequestDTO;
import com.workshop.validador.dto.RecordValidatorDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CSVValidatorService implements RecordValidatorDTO {

    @Override
    public boolean validateRecords(String[] data) {

        boolean validMail = validateMail(data[5]);
        boolean validDate = validateBirthdate(data[7]);
        boolean validJobTitle = validateJobTitle(data[8]);

        return validMail && validDate && validJobTitle;
    }
    public boolean validateMail(String mail) {

        String regex = "^(?!\\.)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);

        return matcher.matches();
    }

    public boolean validateBirthdate(String birhtdate) {
        if (birhtdate.equals("")) {
            return false;
        }
            DateTimeFormatter formatoMmDdYyyy = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate baseDate = LocalDate.of(1980, 1, 1);
            LocalDate inputDate = LocalDate.parse(birhtdate, formatoMmDdYyyy);
            return inputDate.isAfter(baseDate);
    }

    public boolean validateJobTitle(String jobTitle) {
        String[] validJobTitles = {
                "Haematologist",
                "Phytotherapist",
                "Building surveyor",
                "Insurance account manager",
                "Educational psychologist"
        };
        for (String validJobTitle : validJobTitles) {
            if (jobTitle.equalsIgnoreCase(jobTitle)) {
                return true;
            }
        }
        return false;
    }


}


