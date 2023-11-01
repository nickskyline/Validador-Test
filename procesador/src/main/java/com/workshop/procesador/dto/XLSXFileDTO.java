package com.workshop.procesador.dto;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelSheet("SafetyData")
public class XLSXFileDTO {
    @ExcelCell(0)
    private String date;
    @ExcelCell(1)
    private String injuryLocation;
    @ExcelCell(2)
    private String gender;
    @ExcelCell(3)
    private String ageGroup;
    @ExcelCell(4)
    private String incidentType;
    @ExcelCell(5)
    private String daysLost;
    @ExcelCell(6)
    private String plant;
    @ExcelCell(7)
    private String reportType;
    @ExcelCell(8)
    private String shift;
    @ExcelCell(9)
    private String departament;
    @ExcelCell(10)
    private String incidentCost;
    @ExcelCell(11)
    private String wkDay;
    @ExcelCell(12)
    private String month;
    @ExcelCell(13)
    private String year;
}
