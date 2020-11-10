package com.webeservice.example.restfulServices.exception;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDtl = new CustomErrorDetails(new Date(),
				"From MethodArgumentNotValidException in GEH", ex.getMessage());

		return new ResponseEntity<>(customErrorDtl, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDtl = new CustomErrorDetails(new Date(),
				"From HttpRequestMethodNotSupportedException in GEH", ex.getMessage());

		return new ResponseEntity<>(customErrorDtl, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest req) {

		CustomErrorDetails customErrorDtl = new CustomErrorDetails(new Date(), ex.getMessage(),
				req.getDescription(true));

		return new ResponseEntity<>(customErrorDtl, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest req) {

		CustomErrorDetails customErrorDtl = new CustomErrorDetails(new Date(), ex.getMessage(),
				req.getDescription(false));

		return new ResponseEntity<>(customErrorDtl, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest req) {

		CustomErrorDetails customErrorDtl = new CustomErrorDetails(new Date(), ex.getMessage(),
				req.getDescription(true));

		return new ResponseEntity<>(customErrorDtl, HttpStatus.NOT_FOUND);
	}
}
