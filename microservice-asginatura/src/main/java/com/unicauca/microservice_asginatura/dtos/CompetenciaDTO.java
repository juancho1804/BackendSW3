package com.unicauca.microservice_asginatura.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetenciaDTO {
    private Integer id;
    private String descripcion;
    private Nivel nivel; // BASICO, INTERMEDIO, AVANZADO
}
