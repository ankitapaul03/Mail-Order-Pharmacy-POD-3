
package com.mailorderpharmacy.authservice.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AuthResponseTest {

	AuthResponse auth = new AuthResponse();
	AuthResponse auth1 = new AuthResponse("ad", "ad", true);

	@Test
	void testUid() {
		auth.setUid("Uid");
		assertEquals( "Uid", auth.getUid());
	}

	@Test
	void testName() {
		auth.setName("Name");
		assertEquals("Name", auth.getName());
	}

	@Test
	void testIsValid() {
		auth.setValid(true);
		assertEquals( true, auth.isValid());
	}

}
