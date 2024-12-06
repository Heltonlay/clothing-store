package com.daniel.clothing_store.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	private ResponseEntity<ExceptionBody> IdNotFound(NotFoundException e, HttpServletRequest request) {
		String errorDetails = "Id not found on database.";
		int status = HttpStatus.NOT_FOUND.value();
		ExceptionBody body = new ExceptionBody(Instant.now(), e.getMessage(), errorDetails, status,
				request.getRequestURI());

		return ResponseEntity.status(status).body(body);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	private ResponseEntity<ExceptionBody> JsonError(HttpMessageNotReadableException e, HttpServletRequest request) {
		String errorDetails = "Error trying to insert or update object.";
		int status = HttpStatus.BAD_REQUEST.value();
		ExceptionBody body = new ExceptionBody(Instant.now(), e.getMessage(), errorDetails, status,
				request.getRequestURI());

		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	private ResponseEntity<ExceptionBody> IllegalArgument(IllegalArgumentException e, HttpServletRequest request) {
		String errorDetails = "Illegal argument exception.";
		int status = HttpStatus.BAD_REQUEST.value();
		ExceptionBody body = new ExceptionBody(Instant.now(), e.getMessage(), errorDetails, status,
				request.getRequestURI());

		return ResponseEntity.status(status).body(body);
	}
}
