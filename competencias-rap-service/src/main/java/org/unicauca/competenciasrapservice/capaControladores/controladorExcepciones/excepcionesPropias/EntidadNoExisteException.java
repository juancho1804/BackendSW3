package org.unicauca.competenciasrapservice.capaControladores.controladorExcepciones.excepcionesPropias;

import org.unicauca.competenciasrapservice.capaControladores.controladorExcepciones.estructuraExcepciones.CodigoError;

public class EntidadNoExisteException extends RuntimeException {
    private final String llaveMensaje;
    private final String codigo;

    public EntidadNoExisteException(CodigoError code) {
        super(code.getCodigo());
        this.llaveMensaje = code.getCodigo();
        this.codigo = code.getCodigo();
    }

    public EntidadNoExisteException(final String message) {
        super(message);
        this.llaveMensaje = CodigoError.ENTIDAD_NO_ENCONTRADA.getLlaveMensaje();
        this.codigo = CodigoError.ENTIDAD_NO_ENCONTRADA.getCodigo();
    }
}
