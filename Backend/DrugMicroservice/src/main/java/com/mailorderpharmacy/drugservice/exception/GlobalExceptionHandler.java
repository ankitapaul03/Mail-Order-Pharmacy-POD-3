package com.mailorderpharmacy.drugservice.exception;

import feign.RetryableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import com.mailorderpharmacy.drugservice.entity.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler({ InvalidTokenException.class })
    public ResponseEntity<ExceptionResponse> handleInvalidTokenException(final InvalidTokenException e) {
        return new ResponseEntity<>(
        	new ExceptionResponse(
        		e.getMessage(), 
        		LocalDateTime.now(), 
        		HttpStatus.UNAUTHORIZED
        	), 
        	HttpStatus.UNAUTHORIZED
        );
    }
    
    @ExceptionHandler({ DrugNotFoundException.class })
    public ResponseEntity<ExceptionResponse> handleDrugNotFoundException(final DrugNotFoundException e) {
    	return new ResponseEntity<>(
    		new ExceptionResponse(
           		e.getMessage(), 
           		LocalDateTime.now(), 
           		HttpStatus.NOT_FOUND
           	), 
           	HttpStatus.NOT_FOUND
        );
    }
    
    @ExceptionHandler({ StockNotFoundException.class })
    public ResponseEntity<ExceptionResponse> handleStockNotFoundException(final StockNotFoundException e) {
    	return new ResponseEntity<>(
           	new ExceptionResponse(
           		e.getMessage(), 
           		LocalDateTime.now(), 
           		HttpStatus.UNAUTHORIZED
           	), 
           	HttpStatus.UNAUTHORIZED
        );
    }
    
    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ExceptionResponse> handleMicroServiceUnavailableException() {
    	return new ResponseEntity<>(
      		new ExceptionResponse(
           		"Microservice Unavailable", 
           		LocalDateTime.now(), 
           		HttpStatus.NOT_FOUND
           	), 
           	HttpStatus.NOT_FOUND
        );
    }
    
}