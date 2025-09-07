package com.reviewms.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.reviewms.exception.ReviewNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<String> reviewNotFoundException(ReviewNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
