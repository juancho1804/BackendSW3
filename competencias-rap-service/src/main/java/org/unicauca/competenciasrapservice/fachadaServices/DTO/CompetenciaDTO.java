package org.unicauca.competenciasrapservice.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Nivel;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CompetenciaDTO {
    private int id;
    private String descripcion;
    private Nivel nivel;
}
