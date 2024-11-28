package org.unicauca.docenteservice.fachadaServices.services;

import org.unicauca.docenteservice.fachadaServices.DTO.DocenteDTO;

import java.util.List;

public interface IDocenteService {
    public DocenteDTO agregarDocente(DocenteDTO docente);
    public DocenteDTO actualizarDocente(DocenteDTO docente);
    public DocenteDTO eliminarDocente(DocenteDTO docente);
    public List<DocenteDTO> listarDocentes();
}
