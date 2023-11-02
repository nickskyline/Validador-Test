package com.workshop.procesador.dto;

import lombok.Data;

@Data
public class FileDTO {
    private String path;
    private String fileType;

    public FileDTO(String path, String fileType) {
        this.path = path;
        this.fileType = fileType;
    }
}
