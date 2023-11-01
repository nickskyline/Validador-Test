package com.workshop.procesador.service;
import com.workshop.procesador.component.FileProcessorStrategyFactory;
import com.workshop.procesador.dto.FileDTO;
import com.workshop.procesador.dto.FileProcessorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileProcessorStrategyFactory fileProcessorStrategyFactory;

    public Map<String, Integer> uploadFile(FileDTO fileDTO) throws FileNotFoundException {

        String fileType = fileDTO.getFileType();
        String path = fileDTO.getPath();

        FileProcessorDTO getStrategy = fileProcessorStrategyFactory.getStrategy(fileType);

        return getStrategy.fileProcess(path);
    }
}
