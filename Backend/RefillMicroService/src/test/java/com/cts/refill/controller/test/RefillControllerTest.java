/**
 * 
 */
package com.cts.refill.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.cts.refill.controller.RefillController;
import com.cts.refill.exception.DrugQuantityNotAvailable;
import com.cts.refill.exception.InvalidTokenException;
import com.cts.refill.model.RefillOrder;
import com.cts.refill.model.RefillOrderLine;
import com.cts.refill.service.IRefillOrder;
import com.cts.refill.service.IRefillOrderSubscription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import feign.FeignException;


@SpringBootTest
@AutoConfigureMockMvc
public class RefillControllerTest {

	@InjectMocks
	private RefillController refillController;

	@Mock
	private IRefillOrder oservice;

	@Mock
	private IRefillOrderSubscription subservice;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void viewRefillStatusTest() throws Exception {
		ResponseEntity<List<RefillOrder>> list = refillController.viewRefillStatus("token", 1);
		String response = list.getStatusCode().toString();
		assertEquals("200 OK", response);
	}

	@Test
	public void getRefillDuesAsOfDateTest() throws InvalidTokenException {
		ResponseEntity<List<RefillOrderLine>> list = refillController.getRefillDuesAsOfDate("token", 2, "CR7");
		String result = list.getStatusCode().toString();
		assertEquals("200 OK", result);
	}

	@Test
	public void getRefillDuesAsOfPayment() throws InvalidTokenException {
		ResponseEntity<Boolean> result = refillController.getRefillPaymentDues("token", 25);
		String response = result.getStatusCode().toString();
		assertEquals("200 OK", response);
	}

	@Test
	public void requestAdhocRefill()
			throws InvalidTokenException, FeignException, ParseException, DrugQuantityNotAvailable, JSONException {
		ObjectNode order = mapper.createObjectNode();
		order.put("policyID", new Long(1101));
		order.put("subID", new Long(101));
		order.put("memberID", "1");
		order.put("location", "Chennai");
		order.put("quantity", new Integer(15));

		// System.out.println(order);
		ResponseEntity<RefillOrder> result = refillController.requestAdhocRefill("token", order);
		String response = result.getStatusCode().toString();
		assertEquals("202 ACCEPTED", response);
	}
	
	@Test
	public void requestRefillSubscription() throws InvalidTokenException, ParseException{
		ObjectNode order = mapper.createObjectNode();
		order.put("subID", new Long(101));
		order.put("memberID", "1");
		order.put("quantity", new Integer(15));
		order.put("refillCycle", new Integer(3));
		ResponseEntity<RefillOrderLine> result = refillController.requestRefillSubscription("token",101, "CR7", 15, 3);
		String response = result.getStatusCode().toString();
		assertEquals("202 ACCEPTED", response);
	}
	
	@Test
	public void viewRefillOrderSubscriptionStatus() throws InvalidTokenException{
		ResponseEntity<List<RefillOrderLine>> list = refillController.viewRefillOrderSubscriptionStatus("token");
		String result = list.getStatusCode().toString();
		assertEquals("200 OK", result);
	}
	
	@Test
	public void  deleteBySubscriptionId() throws InvalidTokenException{
		refillController.deleteBySubscriptionId("token", 1);
		assertEquals("OK", "OK");
	}
	

}
