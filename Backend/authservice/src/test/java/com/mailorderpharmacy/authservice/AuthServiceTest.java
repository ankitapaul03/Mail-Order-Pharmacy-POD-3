package com.mailorderpharmacy.authservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
 class AuthServiceTest {

	@Test
	 void main() {
		AuthService.main(new String[] {});
		assertTrue(true);
	}
}

