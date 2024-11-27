package com.unicauca.microservice_asginatura.exceptions;


import com.unicauca.microservice_asginatura.service.EnumErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Error de un producto
 * 
 * @author juan, pisso
 *
 */
@Data
@Builder
@AllArgsConstructor
public class AsignaturaError {
	/**
	 * Codigo del error
	 */
	public final EnumErrorCodes code;
	/**
	 * Campo del error
	 */
	public final String field;
	/**
	 * Descripción del error
	 */
	public final String description;


}
