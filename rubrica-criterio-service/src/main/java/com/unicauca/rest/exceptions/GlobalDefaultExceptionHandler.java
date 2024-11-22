package com.unicauca.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * Para capturar errores o excepciones de manera global, se crear esta clase
 * anotada con @ControllerAdvic. Aquí define cada tipo de error que se desea
 * manejar con @ExceptionHandler. Este método recibe la excepción en particular.
 * Se puede manejar todas las excepciones que se desee.
 * 
 * @author wpantoja, ahurtado
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	/**
	 * Método para manejar la excepcion ResourceNotFoundException
	 * 
	 * @param e ResourceNotFoundException
	 * @return la excepción en formato Json
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@RequestMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<>("{\"errors\":[{\"code\":\"NOT_FOUND\"}]}", HttpStatus.NOT_FOUND);

	}

	/**
	 * Método para manejar la excepcion ProductDomainException
	 * 
	 * @param e ProductDomainException
	 * @return la excepción en formato Json
	 */
	@ExceptionHandler(RubricaDomainException.class)
	@RequestMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<ErrorsPayload> handleTodoDomainException(RubricaDomainException e) {
		return new ResponseEntity<>(new ErrorsPayload(e.errors), HttpStatus.UNPROCESSABLE_ENTITY);

	}
}
