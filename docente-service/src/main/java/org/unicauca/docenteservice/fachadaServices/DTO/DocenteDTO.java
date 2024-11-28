package org.unicauca.docenteservice.fachadaServices.DTO;

import lombok.*;
import org.unicauca.docenteservice.capaAccesoADatos.models.EEstado;
import org.unicauca.docenteservice.capaAccesoADatos.models.ETipoDocente;
import org.unicauca.docenteservice.capaAccesoADatos.models.ETipoIdentificacion;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DocenteDTO {
    private Long id;
    private ETipoIdentificacion tipoIdentificacion;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String email;
    private String tituloAcademico;
    private EEstado estado;
    private String contrasenia;
    private ETipoDocente tipoDocente;
}
