package com.workshop.procesador.feign;

import com.workshop.procesador.configuration.FeignClientConfig;
import com.workshop.procesador.dto.FileRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

@FeignClient(name = "DOCUMENT-MOCK-API", url = "http://localhost:8080", configuration = FeignClientConfig.class)
public interface FeignFileClient {

    @PostMapping(value = "api/v1/files", consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean upload(@RequestBody FileRequestDTO data);

}
