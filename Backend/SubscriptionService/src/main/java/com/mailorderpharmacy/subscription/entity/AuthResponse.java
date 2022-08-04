package com.mailorderpharmacy.subscription.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	
	// User Id 
	private String uid;
	
	// User Name 
	private String name;
	
	// Validity check 
	private boolean isValid;

	
}
