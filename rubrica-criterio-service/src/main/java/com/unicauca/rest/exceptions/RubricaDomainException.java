package com.unicauca.rest.exceptions;

import java.util.List;


public class RubricaDomainException extends Exception{
    /**
     * Listado de errores
     */
    public final List<RubricaError> errors;

    public RubricaDomainException(List<RubricaError> errors) {
        this.errors = errors;
    }
}
