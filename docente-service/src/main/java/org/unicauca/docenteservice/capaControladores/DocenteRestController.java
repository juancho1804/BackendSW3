package org.unicauca.docenteservice.capaControladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unicauca.docenteservice.fachadaServices.DTO.DocenteDTO;
import org.unicauca.docenteservice.fachadaServices.services.IDocenteService;

@RestController
@RequestMapping("/docentes")
public class DocenteRestController {
    @Autowired
    private IDocenteService docenteService;

    @PostMapping
    public void agregar(@RequestBody DocenteDTO docenteDTO){
        System.out.println("Entrando a agregar...");
        docenteService.agregarDocente(docenteDTO);
    }
}
