package com.mailorderpharmacy.authservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mailorderpharmacy.authservice.service.UnauthorizedException;

@SpringBootTest
 class UnauthorizedExceptionTest {
	
	
	
	@Test
	 void constructortest()
	{
		UnauthorizedException unauthorizedException =new UnauthorizedException("unauthorized");
		//System.out.println(unauthorizedException.getMessage());
		assertEquals("unauthorized", unauthorizedException.getMessage());
	}

}
