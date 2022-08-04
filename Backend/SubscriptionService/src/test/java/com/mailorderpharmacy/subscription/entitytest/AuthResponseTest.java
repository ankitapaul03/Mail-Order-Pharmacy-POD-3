package com.mailorderpharmacy.subscription.entitytest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.mailorderpharmacy.subscription.entity.AuthResponse;


 class AuthResponseTest {
	AuthResponse auth = new AuthResponse();
	AuthResponse auth2 = new AuthResponse("Uid","Name",true);
	@Test
	void testUid() {
		auth.setUid("Uid");
		assertEquals("Uid", auth.getUid());
	}

	@Test
	void testName() {
		auth.setName("Name");
		assertEquals("Name", auth.getName());
	}

	@Test
	void testIsValid() {
		auth.setValid(true);
		assertEquals(true, auth.isValid());
	}
	
	@Test
	void testToString() {
		String str = auth2.toString();
		assertEquals(auth2.toString(), str);
	}
}
