package com.collabera.todorest.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(TodoNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> TodoNotFound(TodoNotFoundException e)
	{
		ExceptionResponse eResponse = new ExceptionResponse(e.getMessage(), "Details of business logic");
		
		return new ResponseEntity<ExceptionResponse>(eResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> genericException(Exception e)
	{
		ExceptionResponse eResponse = new ExceptionResponse(e.getMessage(), "Generic Exception");
		
		return new ResponseEntity<ExceptionResponse>(eResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
}
