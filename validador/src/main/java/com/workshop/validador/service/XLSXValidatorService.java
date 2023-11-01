package com.workshop.validador.service;

import com.workshop.validador.dto.FileRequestDTO;
import com.workshop.validador.dto.RecordValidatorDTO;
import org.springframework.stereotype.Component;

@Component
public class XLSXValidatorService implements RecordValidatorDTO {
    @Override
    public boolean validateRecords(String[] data) {
        return isValidInjuryLocation(data[0]) && isValidReportType(data[1]);
    }

    private boolean isValidInjuryLocation(String injuryLocation) {
        return !"N/A".equals(injuryLocation);
    }

    private boolean isValidReportType(String reportType) {
        return "Near Miss".equals(reportType) || "Lost Time".equals(reportType) || "First Aid".equals(reportType);
    }
}
