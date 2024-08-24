package com.main.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	// Handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", new Date());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	// Handle global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", new Date());
		body.put("message", "An unexpected error occurred");
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Handle validation exceptions
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", new Date());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
