package com.mailorderpharmacy.authservice.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
 class MessageResponseTest {
	
	MessageResponse msg = new MessageResponse();
	MessageResponse msg1 = new MessageResponse("error",LocalDateTime.now());
	
	@Test
	void testMsg() {
		msg.setMessage("error");
		assertEquals( "error", msg.getMessage());
	}

	
	@Test
	void testDate() {
		LocalDateTime date = LocalDateTime.now(); 
		msg.setDate(date);
		assertEquals( date, msg.getDate());
	}

	
	
	
	

}
