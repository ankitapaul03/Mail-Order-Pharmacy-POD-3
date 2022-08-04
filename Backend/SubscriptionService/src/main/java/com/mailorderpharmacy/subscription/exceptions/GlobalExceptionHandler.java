package com.mailorderpharmacy.subscription.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mailorderpharmacy.subscription.entity.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorMessage> handleInvalidTokenException(InvalidTokenException e) {
		return new ResponseEntity<>(
			new ErrorMessage(
				HttpStatus.UNAUTHORIZED, 
				LocalDateTime.now(),
				e.getMessage()
			),
			HttpStatus.UNAUTHORIZED
		);
	}

	@ExceptionHandler(feign.RetryableException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ErrorMessage serviceUnavailableException() {
		return new ErrorMessage(
			HttpStatus.SERVICE_UNAVAILABLE, 
			LocalDateTime.now(), 
			"Temporarily service unavailable"
		);
	}

	@ExceptionHandler(SubscriptionListEmptyException.class)
	public ResponseEntity<ErrorMessage> handleSubscriptionListEmptyException(
			SubscriptionListEmptyException e) {
		return new ResponseEntity<>(
			new ErrorMessage(
				HttpStatus.UNAUTHORIZED, 
				LocalDateTime.now(),
				e.getMessage()
			),
			HttpStatus.NOT_FOUND
		);
	}
	
	@ExceptionHandler(PaymentUnclearException.class)
	public ResponseEntity<ErrorMessage> handlePaymentUnclearException(
			PaymentUnclearException e) {
		return new ResponseEntity<>(
			new ErrorMessage(
				HttpStatus.BAD_REQUEST, 
				LocalDateTime.now(),
				e.getMessage()
			),
			HttpStatus.BAD_REQUEST
		);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleGenericException (Exception e) {
		return new ResponseEntity<>(
			new ErrorMessage(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				LocalDateTime.now(),
				e.getMessage()
			),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}

}