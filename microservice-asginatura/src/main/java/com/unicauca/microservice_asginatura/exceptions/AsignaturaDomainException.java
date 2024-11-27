package com.unicauca.microservice_asginatura.exceptions;

import java.util.List;

/**
 * Lista de errores del dominio para un producto
 * 
 * @author juan, pisso
 *
 */
public class AsignaturaDomainException extends Exception {
	/**
	 * Listado de errores
	 */
	public final List<AsignaturaError> errors;

	public AsignaturaDomainException(List<AsignaturaError> errors) {
		this.errors = errors;
	}
}
