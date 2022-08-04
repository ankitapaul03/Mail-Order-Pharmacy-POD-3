package com.mailorderpharmacy.subscription.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintErrorResponse 
{
	
	private HttpStatus httpStatus;
	private LocalDateTime localDateTime;
	private List<String> message;
	
}