package org.unicauca.competenciasrapservice.fachadaServices.services;

import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Competencia;
import org.unicauca.competenciasrapservice.fachadaServices.DTO.CompetenciaDTO;

import java.util.List;

public interface ICompetenciaService {

    /**
     * @brief Método que devuelve todas las competencias que se encuentran en la base de datos.
     */
    List<CompetenciaDTO> listarCompetencias();
    CompetenciaDTO agregarCompetencia(CompetenciaDTO competencia);
    CompetenciaDTO editarCompetencia(int id,CompetenciaDTO competencia);
    boolean eliminarCompetencia(int id);
    public List<Competencia> obtenerCompetenciasPorIds(List<Integer> ids) ;
     List<CompetenciaDTO> validarCompetencias(List<Integer> ids);
}
