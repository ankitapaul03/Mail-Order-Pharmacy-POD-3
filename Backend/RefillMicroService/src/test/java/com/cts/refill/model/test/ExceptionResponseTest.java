/**
 * 
 */
package com.cts.refill.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.cts.refill.model.ExceptionResponse;


public class ExceptionResponseTest {
	
	LocalDateTime date = LocalDateTime.now();
	ExceptionResponse response1 = new ExceptionResponse();
	ExceptionResponse response2 = new ExceptionResponse("Success",date,HttpStatus.OK);
	
	@Test
	void testMessage() {
		response1.setMessge("Success");
		assertEquals("Success", response1.getMessge());
	}
	
	@Test
	void testDate() {
		response1.setTimestamp(date);
		assertEquals(date, response1.getTimestamp());
	}
	
	@Test
	void testHttpstatus() {
		response1.setStatus(HttpStatus.OK);
		assertEquals(HttpStatus.OK, response1.getStatus());
	}
	
}
