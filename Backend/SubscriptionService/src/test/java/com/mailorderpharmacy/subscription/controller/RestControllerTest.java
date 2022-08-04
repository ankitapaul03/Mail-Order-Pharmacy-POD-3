package com.mailorderpharmacy.subscription.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;
import com.mailorderpharmacy.subscription.entity.SubscriptionDetails;
import com.mailorderpharmacy.subscription.exceptions.InvalidTokenException;
import com.mailorderpharmacy.subscription.exceptions.PaymentUnclearException;
import com.mailorderpharmacy.subscription.exceptions.SubscriptionListEmptyException;
import com.mailorderpharmacy.subscription.services.SubscriptionService;

import feign.FeignException;

@SpringBootTest
@AutoConfigureMockMvc
class RestControllerTest {

	@InjectMocks
	SubscriptionController subscriptionRestcontroller;

	@Mock
	private SubscriptionService subscriptionService;

	@Autowired
	MockMvc mockMvc;

	@Test
	 void subscribeTest() throws Exception {
		PrescriptionDetails prescriptionDetails = new PrescriptionDetails(12001L, "admin", "chennai", "12001",
				"chennai", LocalDate.now(), "Drug1", "weekly", 1, 3, "prakash");
		ResponseEntity<String> res = new ResponseEntity<String>(
				"You have succesfully subscribed to " + prescriptionDetails.getDrugName(), HttpStatus.OK);
		when(subscriptionService.subscribe(prescriptionDetails, "Bearer Token")).thenReturn(res);
		assertEquals(res.getStatusCodeValue(),
				subscriptionRestcontroller.subscribe("Bearer Token", prescriptionDetails).getStatusCodeValue());
	}

	@Test
	 void getAllSubscriptionsTest() throws Exception {
		PrescriptionDetails prescriptionDetails = new PrescriptionDetails(12001L, "admin", "chennai", "12001",
				"chennai", LocalDate.now(), "Drug1", "weekly", 1, 3, "prakash");
		SubscriptionDetails subscriptionDetails = new SubscriptionDetails(prescriptionDetails.getPrescriptionId(),
				prescriptionDetails.getCourseDuration(), prescriptionDetails.getQuantity(),
				prescriptionDetails.getMemberId(), prescriptionDetails.getPrescriptionDate(),
				prescriptionDetails.getMemberLocation(), "paid", prescriptionDetails.getDrugName());
		List<SubscriptionDetails> list = new ArrayList<>();
		list.add(subscriptionDetails);
		ResponseEntity<String> res = new ResponseEntity<String>(
				"You have succesfully subscribed to " + prescriptionDetails.getDrugName(), HttpStatus.OK);
		when(subscriptionService.subscribe(prescriptionDetails, "Bearer Token")).thenReturn(res);
		when(subscriptionRestcontroller.getAllSubscriptionsforMember("Bearer Token", "admin")).thenReturn(list);

		String actual = "<200 OK OK,You have succesfully subscribed to Drug1,[]>";
		assertEquals(res.toString(),actual);

	}

	@Test
	 void getDrugNameTest() throws InvalidTokenException, SubscriptionListEmptyException {
		PrescriptionDetails prescriptionDetails = new PrescriptionDetails(12001L, "admin", "chennai", "12001",
				"chennai", LocalDate.now(), "Drug1", "weekly", 1, 3, "prakash");
		ResponseEntity<String> res = new ResponseEntity<String>(
				"You have succesfully subscribed to - " + prescriptionDetails.getDrugName(), HttpStatus.OK);
		when(subscriptionService.subscribe(prescriptionDetails, "Bearer Token")).thenReturn(res);
		ResponseEntity<String> drugname = new ResponseEntity<String>(prescriptionDetails.getDrugName(), HttpStatus.OK);
		when(subscriptionService.getDrugNameBySubscriptionId(prescriptionDetails.getPrescriptionId(), "Bearer Token"))
				.thenReturn(drugname);
		when(subscriptionRestcontroller.getDrugNameBySubscriptionId("Bearer Token",
				prescriptionDetails.getPrescriptionId())).thenReturn(drugname);

		String actual = "<200 OK OK,You have succesfully subscribed to - Drug1,[]>";
		assertEquals(res.toString(),actual);


	}

	@Test
	 void unsubscribeTest() throws InvalidTokenException, FeignException, PaymentUnclearException {
		PrescriptionDetails prescriptionDetails = new PrescriptionDetails(12001L, "admin", "chennai", "12001",
				"chennai", LocalDate.now(), "Drug1", "weekly", 1, 3, "prakash");
		ResponseEntity<String> res = new ResponseEntity<String>(
				"You have succesfully subscribed to " + prescriptionDetails.getDrugName(), HttpStatus.OK);
		when(subscriptionService.subscribe(prescriptionDetails, "Bearer Token")).thenReturn(res);
		ResponseEntity<String> response = new ResponseEntity<String>("You have succesfully Unsubscribed",
				HttpStatus.OK);
		when(subscriptionService.unsubscribe(prescriptionDetails.getMemberId(), prescriptionDetails.getPrescriptionId(),
				"Bearer Token")).thenReturn(response);
		assertEquals( 200, subscriptionRestcontroller
				.unsubscribe("Bearer Token", prescriptionDetails.getMemberId(), prescriptionDetails.getPrescriptionId())
				.getStatusCodeValue());
	}
}
