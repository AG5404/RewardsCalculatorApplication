package com.example.rewards.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rewards.models.APIError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIError> handleRuntimeException(ResourceNotFoundException ex, HttpServletRequest request){
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		APIError apiError = new APIError(httpStatus.value(), httpStatus.name(), ex.getMessage(), LocalDateTime.now(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		APIError apiError = new APIError(httpStatus.value(), httpStatus.name(), ex.getMessage(), LocalDateTime.now(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
}
