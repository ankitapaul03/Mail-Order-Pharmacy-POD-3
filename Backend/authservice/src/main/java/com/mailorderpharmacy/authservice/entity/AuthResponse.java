package com.mailorderpharmacy.authservice.entity;

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
	// Id for user
	private String uid;
	// Name of the user
	private String name;
	// Validity check
	private boolean isValid;

}
