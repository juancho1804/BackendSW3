package org.unicauca.competenciasrapservice.fachadaServices.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Competencia;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RapDTO {
    private int id;
    private String descripcion;
    private CompetenciaDTO competencia;
}
