package com.unicauca.rest.exceptions;


import com.unicauca.rest.service.EnumErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Error de un producto
 * 
 * @author wpantoja, ahurtado
 *
 */
@Data
@Builder
@AllArgsConstructor
public class CriterioError {
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
