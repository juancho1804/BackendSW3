package org.unicauca.competenciasrapservice.fachadaServices.services;

import org.unicauca.competenciasrapservice.capaAccesoADatos.models.Rap;
import org.unicauca.competenciasrapservice.fachadaServices.DTO.RapDTO;

import java.util.List;

public interface IRapService {
    List<RapDTO> listarRAP();
    RapDTO agregarRap(RapDTO rap);
    RapDTO editarRap(int id,RapDTO rap);
    boolean eliminarRap(int id);
}
