package com.unicauca.microservice_asginatura.client;


import com.unicauca.microservice_asginatura.dtos.CompetenciaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// Define el cliente Feign para conectarse al microservicio de competencias
@FeignClient(name = "competencias", url = "http://localhost:8001/competenciasrap")

public interface CompetenciaClient {
    @PostMapping("/validar")
    List<CompetenciaDTO> validarCompetencias(@RequestBody List<Integer> ids);
}
