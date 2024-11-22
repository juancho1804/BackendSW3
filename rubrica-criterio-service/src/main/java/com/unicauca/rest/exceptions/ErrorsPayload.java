package com.unicauca.rest.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilizada por GlobalDefaultExceptionHandler para manerjar los errores.
 * 
 * @author wpantoja, ahurtado
 *
 */
public class ErrorsPayload {

	public final List<ErrorJSON> errors;

	public ErrorsPayload(List<RubricaError> applicationErrors) {
		this.errors = new ArrayList<>();
		applicationErrors.forEach(applicationError -> errors.add(fromApplicationError(applicationError)));
	}

	private ErrorJSON fromApplicationError(RubricaError error) {
		return new ErrorJSON(error.code, error.field, error.description);
	}
}
