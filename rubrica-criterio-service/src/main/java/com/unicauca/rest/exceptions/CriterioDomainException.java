package com.unicauca.rest.exceptions;

import java.util.List;

/**
 * Lista de errores del dominio para un producto
 * 
 * @author wpantoja, ahurtado
 *
 */
public class CriterioDomainException extends Exception {
	/**
	 * Listado de errores
	 */
	public final List<CriterioError> errors;

	public CriterioDomainException(List<CriterioError> errors) {
		this.errors = errors;
	}
}
