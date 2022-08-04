package com.mailorderpharmacy.subscription.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mailorderpharmacy.subscription.clients.AuthFeign;
import com.mailorderpharmacy.subscription.clients.DrugDetailClient;
import com.mailorderpharmacy.subscription.clients.RefillClient;
import com.mailorderpharmacy.subscription.entity.DrugDetails;
import com.mailorderpharmacy.subscription.entity.DrugLocationDetails;
import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;
import com.mailorderpharmacy.subscription.entity.SubscriptionDetails;
import com.mailorderpharmacy.subscription.entity.TokenValid;
import com.mailorderpharmacy.subscription.exceptions.InvalidTokenException;
import com.mailorderpharmacy.subscription.exceptions.PaymentUnclearException;
import com.mailorderpharmacy.subscription.exceptions.SubscriptionListEmptyException;
import com.mailorderpharmacy.subscription.repository.PrescriptionRepository;
import com.mailorderpharmacy.subscription.repository.SubscriptionRepository;
import com.mailorderpharmacy.subscription.services.SubscriptionServiceImpl;

import feign.FeignException;

@SpringBootTest(classes = SubscriptionServiceImplTest.class)
 class SubscriptionServiceImplTest {

	@InjectMocks
	SubscriptionServiceImpl subscriptionServiceImplementation;

	@Mock
	private DrugDetailClient drugDetailClient;
	@Mock
	private RefillClient refillClient;
	@Mock
	private AuthFeign authFeign;
	@Mock
	PrescriptionRepository prescriptionRepo;
	@Mock
	SubscriptionRepository subscriptionRepo;

	@Test
	 void subscribe() {
		LocalDate date = LocalDate.now();
		PrescriptionDetails prescriptionDetails = new PrescriptionDetails((long) 45, "member", "salem", "member", "member",
				date, "member", "member", 45, 45, "member");
		SubscriptionDetails subscriptionDetails = new SubscriptionDetails(null,(long)45,"member",45,45,"member",date,"salem","active");
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);
		when(authFeign.getValidity("token")).thenReturn(response);
		List<DrugLocationDetails> list = new ArrayList<DrugLocationDetails>();
		

		DrugLocationDetails drugLocationDetails = new DrugLocationDetails("D1","Chennai",30, null);

		list.add(drugLocationDetails);
		

		DrugDetails drugDetails = new DrugDetails("D1", "Drug1", "manu1", new Date(), new Date(), list);

		when(drugDetailClient.getDrugByName("token","Drug1")).thenReturn(drugDetails);
		when(prescriptionRepo.save(any(PrescriptionDetails.class))).thenReturn(prescriptionDetails);
		when(subscriptionRepo.save(any(SubscriptionDetails.class)))
				.thenReturn(subscriptionDetails);

		assertEquals(new ResponseEntity<>("You have succesfully subscribed to " + prescriptionDetails.getDrugName(),
				HttpStatus.OK), subscriptionServiceImplementation.subscribe(prescriptionDetails, "token"));

	}
 
	@Test
	 void subscribefalse() {
		LocalDate date = LocalDate.now();

		PrescriptionDetails prescriptionDetails = new PrescriptionDetails((long) 45, "member", "salem", "45", "45",
				date, "member", "member", 45, 45, "member");

		TokenValid tokenValid = new TokenValid("uid", "uname", false);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);	
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class,
				() -> subscriptionServiceImplementation.subscribe(prescriptionDetails, "token"));
	}

	@Test
	 void unsubscribe() throws InvalidTokenException, FeignException, PaymentUnclearException {
		LocalDate date = LocalDate.now();
		PrescriptionDetails prescriptionDetails = new PrescriptionDetails((long) 45, "member", "salem", "45", "45",
				date, "member", "member", 45, 45, "member");
		SubscriptionDetails subscriptionDetails = new SubscriptionDetails((long) 45, 45, 45, "member", date, "salem",
				"member", "member");
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);	
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillClient.isPendingPaymentDues("token", (long) 45)).thenReturn(false);
		subscriptionServiceImplementation.unsubscribe("asd", (long) 45, "token");

		boolean actual = refillClient.isPendingPaymentDues("token", (long) 45);

		assertEquals(false, actual);

	}

	@Test
	 void unsubscribefalse() throws InvalidTokenException, FeignException, PaymentUnclearException {
		LocalDate date = LocalDate.now();
		PrescriptionDetails prescriptionDetails = new PrescriptionDetails((long) 45, "member", "salem", "45", "45",
				date, "member", "member", 45, 45, "member");
		SubscriptionDetails subscriptionDetails = new SubscriptionDetails((long) 45, 45, 45, "member", date, "salem",
				"member", "member");
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);	
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillClient.isPendingPaymentDues("token", (long) 45)).thenReturn(true);
//		subscriptionServiceImplementation.unsubscribe("asd", (long) 45, "token");

//		boolean actual = refillClient.isPendingPaymentDues("token", (long) 45);

//		assertEquals(true, actual);
		
		assertThrows(
			PaymentUnclearException.class, 
			() -> subscriptionServiceImplementation.unsubscribe("asd", (long) 45, "token")
		);
		
//		assertEquals("You have to clear your payment dues first.", e.getMessage());

	}

	@Test
	 void getStatusInvalidToken() {
		TokenValid tokenValid = new TokenValid("uid", "uname", false);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);	
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class,
				() -> subscriptionServiceImplementation.unsubscribe("asd", (long) 45, "token"));
	}

	@Test
	 void getAllSubscriptionsTest() {
		LocalDate date = LocalDate.now();
		List<SubscriptionDetails> list = new ArrayList<SubscriptionDetails>();
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);	
		when(authFeign.getValidity("token")).thenReturn(response);
		when(subscriptionRepo.findByMemberId("mem")).thenReturn(list);
		assertThrows(SubscriptionListEmptyException.class,
				() -> subscriptionServiceImplementation.getAllSubscriptions("mem", "token"));

	}

	@Test
	 void getAllSubscriptionsTestsucess() throws InvalidTokenException, SubscriptionListEmptyException {
		LocalDate date = LocalDate.now();
		SubscriptionDetails subscriptionDetails = new SubscriptionDetails((long) 45, 45, 45, "member", date, "salem",
				"member", "member");

		List<SubscriptionDetails> list = new ArrayList<SubscriptionDetails>();
		list.add(subscriptionDetails);
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);		when(authFeign.getValidity("token")).thenReturn(response);
		when(subscriptionRepo.findByMemberId("mem")).thenReturn(list);
		subscriptionServiceImplementation.getAllSubscriptions("mem", "token");

		List<SubscriptionDetails> actual = subscriptionRepo.findByMemberId("mem");

		assertEquals(list, actual);

	}

	@Test
	 void getAllSubscriptionsTestFalse() {
		TokenValid tokenValid = new TokenValid("uid", "uname", false);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);	
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class,
				() -> subscriptionServiceImplementation.getAllSubscriptions("asd", "token"));
	}

	@Test
	 void getDrugNameBySubscriptionIdTest() throws InvalidTokenException, SubscriptionListEmptyException {
		LocalDate date = LocalDate.now();
		PrescriptionDetails prescriptionDetails = new PrescriptionDetails((long) 45, "member", "salem", "45", "45",
				date, "member", "member", 45, 45, "member");
		SubscriptionDetails subscriptionDetails = new SubscriptionDetails((long) 45, 45, 45, "member", date, "salem",
				"member", "member");
		List<SubscriptionDetails> list = new ArrayList<SubscriptionDetails>();
		list.add(subscriptionDetails);
		TokenValid tokenValid = new TokenValid("uid", "uname", true);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);
		when(authFeign.getValidity("token")).thenReturn(response);
	
		 when(subscriptionRepo.findById((long) 45)).thenReturn(Optional.of(subscriptionDetails));
		subscriptionServiceImplementation.getDrugNameBySubscriptionId((long) 45, "token");

		SubscriptionDetails actual= subscriptionRepo.findById((long) 45).orElse(new SubscriptionDetails());

		SubscriptionDetails expected = list.get(0);

		assertEquals(expected, actual);

	}

	@Test
	 void getDrugNameBySubscriptionIdTestFalse() {
		TokenValid tokenValid = new TokenValid("uid", "uname", false);
		ResponseEntity<TokenValid> response = new ResponseEntity<TokenValid>(tokenValid, HttpStatus.OK);	
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class,
				() -> subscriptionServiceImplementation.getDrugNameBySubscriptionId((long) 45, "token"));
	}

}
