/**
 * 
 */
package com.cts.refill.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.cts.refill.model.RefillOrderLine;


public class RefillOrderLineTest {
	RefillOrderLine test = new RefillOrderLine();
	RefillOrderLine test2 = new RefillOrderLine(1, 1, "CR7", 3, 25);

	@Test
	public void testRefillID() {
		test.setId(1);
		assertEquals(1, test.getId());
		assertEquals(1, test2.getId());
	}

	@Test
	public void testsubscriptionId() {
		test.setSubID(1);
		assertEquals(1, test.getSubID());
		assertEquals(1, test2.getSubID());
	}

	@Test
	public void testRefillQuantity() {
		test.setQuantity(25);
		assertEquals(25, test.getQuantity());
		assertEquals(25, test2.getQuantity());
	}

	@Test
	public void testMemberId() {
		test.setMemberID("CR7");
		assertEquals("CR7", test.getMemberID());
		assertEquals("CR7", test2.getMemberID());
	}

	@Test
	public void testRefillTime() {
		test.setRefillTime(3);
		assertEquals(3, test.getRefillTime());
		assertEquals(3, test2.getRefillTime());

	}
}
