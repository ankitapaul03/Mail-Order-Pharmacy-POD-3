/**
 * 
 */
package com.cts.refill.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.cts.refill.model.RefillOrder;


public class RefillOrderTest {
	Date date = new Date();
	RefillOrder test = new RefillOrder();
	RefillOrder test2= new RefillOrder(1, 1, "CR7", true, date, 25);
	
	@Test
	public void testRefillID() {
		test.setRefillID(1);
		assertEquals(1, test.getRefillID());
		assertEquals(1, test2.getRefillID());
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
	public void testPayStatus() {
		test.setPayStatus(true);
		assertEquals(true, test.getPayStatus());
		assertEquals(true, test2.getPayStatus());
	}
	
	@Test
	public void testrefilledDate() {
		test.setRefillDate(date);
		assertEquals(date, test.getRefillDate());
		assertEquals(date, test2.getRefillDate());
	}
	
}
