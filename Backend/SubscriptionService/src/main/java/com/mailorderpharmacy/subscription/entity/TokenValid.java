package com.mailorderpharmacy.subscription.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**Model class for the business details*/

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenValid {
	
	// User Id
	private String uid;
	
	// User Name 
	private String name;
	
	// Validity check for the token 
	private boolean isValid;

	
	
}
