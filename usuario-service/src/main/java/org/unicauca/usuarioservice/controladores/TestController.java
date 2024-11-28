package org.unicauca.usuarioservice.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.User;
import org.unicauca.usuarioservice.configuracionSeguridad.fachada.services.CrudImpl;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private CrudImpl crud;

    @GetMapping("/all")
    public String allAccess() {
        return "Contenido publico";
    }



    @GetMapping("/{identificacion}")
    public Optional<User> obtenerUsuarioPorIdentificacion(@PathVariable("identificacion") String identificacion) {
        //String identificacion = "12345678101010";
        return crud.obtenerUsuarioPorIdentificacion(identificacion);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "Contenido privado. Datos retornados para el api de usuarios.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Contenido privado. Datos retornados para el api de moderador.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Contenido privado. Datos retornados para el api de administrador.";
    }
}
