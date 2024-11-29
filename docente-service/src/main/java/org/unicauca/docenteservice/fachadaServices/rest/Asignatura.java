package org.unicauca.docenteservice.fachadaServices.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {
    @JsonProperty("asignaturaId")
    private int asignaturaId;

    @JsonProperty("numCredito")
    private int numCredito;

    @JsonProperty("semestreAsignatura")
    private int semestreAsignatura;

    @JsonProperty("estado")
    private boolean estado;

    @JsonProperty("competenciasIds")
    private List<Integer>competenciasIds;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("descripcion")
    private String descripcion;
}
