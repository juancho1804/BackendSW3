package org.unicauca.usuarioservice.configuracionSeguridad.fachada.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.ETipoIdentificacion;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.TipoIdentificacion;

import java.util.Set;

@Getter
@Setter
public class SignupRequestDTO {

   private String tipoIdentificacion;
    @NotBlank
   private String identificacion;
    @NotBlank
   private String nombres;
    @NotBlank
   private String apellidos;
   @NotBlank
    @Email
   private String email;
   @NotBlank
   private String titulo;
   @NotBlank
   private String estado;
   @NotBlank
   private String contrasenia;

   private Set<String>role;

}
