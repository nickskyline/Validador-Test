package com.workshop.procesador.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileRequestDTO {
    private String type;
    private String[] records;
}
