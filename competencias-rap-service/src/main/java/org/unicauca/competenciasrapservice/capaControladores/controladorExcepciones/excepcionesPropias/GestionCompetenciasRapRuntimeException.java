package org.unicauca.competenciasrapservice.capaControladores.controladorExcepciones.excepcionesPropias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.unicauca.competenciasrapservice.capaControladores.controladorExcepciones.estructuraExcepciones.CodigoError;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class GestionCompetenciasRapRuntimeException extends RuntimeException {
    protected CodigoError codigoError;
    public abstract String formatException();
}
