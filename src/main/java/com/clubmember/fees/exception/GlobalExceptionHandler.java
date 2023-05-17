package com.clubmember.fees.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex,WebRequest request)
	{
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidFeesAmountException.class)
	public ResponseEntity<ErrorMessage> invalidFeesAmountException(InvalidFeesAmountException ex,WebRequest request)
	{
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalException(Exception ex,WebRequest request)
	{
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}
