package org.unicauca.docenteservice.fachadaServices.services;

import org.unicauca.docenteservice.fachadaServices.DTO.DocenteDTO;
import java.util.List;

public interface IDocenteService {
    public DocenteDTO agregarDocente(DocenteDTO docente);
    public DocenteDTO actualizarDocente(DocenteDTO docente, String id);
    public boolean cambiarEstado(int id);
    public List<DocenteDTO> listarDocentes();
    public List<Integer> encontrarAsignaturas(List<Integer> ids);
    public DocenteDTO agregarAsignatura(String idDocente, List<Integer> idsAsignaturas);
}
