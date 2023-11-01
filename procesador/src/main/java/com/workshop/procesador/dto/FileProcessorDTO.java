package com.workshop.procesador.dto;

import java.io.FileNotFoundException;
import java.util.Map;

public interface FileProcessorDTO {
    Map<String, Integer> fileProcess(String path) throws FileNotFoundException;
}
