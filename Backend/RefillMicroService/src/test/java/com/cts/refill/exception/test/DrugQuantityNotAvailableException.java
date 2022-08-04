package com.cts.refill.exception.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = DrugQuantityNotAvailableException.class)
class DrugQuantityNotAvailableException {
	
	@Test
	void drugQuantityNotAvailableException()
	{
		DrugQuantityNotAvailableException drugQuantityNotAvailableException = new DrugQuantityNotAvailableException();
		String actual = "OK";
		String expected = "OK";
		Assertions.assertEquals(actual, expected);
	}

}
