package org.unicauca.docenteservice.fachadaServices.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("tipoIdentificacion")
    private String tipoIdentificacion;
    @JsonProperty("identificacion")
    private String identificacion;
    @JsonProperty("nombres")
    private String nombres;
    @JsonProperty("apellidos")
    private String apellidos;
    @JsonProperty("email")
    private String email;
    @JsonProperty("contrasenia")
    private String contrasenia;
}
