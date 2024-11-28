package com.unicauca.microservice_asginatura.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaDTO {
    private String nombre;
    private String descripcion;
    private Integer numCredito;
    private Integer semestreAsignatura;
    private Boolean estado;
    private List<CompetenciaDTO> competencias;
    private List<Integer> competenciasIds;
    // Lista de IDs de competencias
}
