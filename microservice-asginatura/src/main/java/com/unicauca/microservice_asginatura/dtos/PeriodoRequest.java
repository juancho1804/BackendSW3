package com.unicauca.microservice_asginatura.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoRequest {
    private Long asignaturaCompetenciaId;
    private LocalDate periodoIni;
    private LocalDate periodoFin;
}
