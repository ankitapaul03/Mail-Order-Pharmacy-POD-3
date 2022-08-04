package com.cts.refill.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.refill.exception.InvalidTokenException;
import com.cts.refill.exception.SubscriptionIDNotFoundException;
import com.cts.refill.feign.AuthFeign;
import com.cts.refill.feign.DrugClient;
import com.cts.refill.feign.SubscriptionClient;
import com.cts.refill.model.RefillOrder;
import com.cts.refill.model.TokenValid;
import com.cts.refill.repo.RefillOrderRepository;
import com.cts.refill.service.RefillOrderServiceImpl;
import com.cts.refill.service.RefillorderSubscriptionServiceImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class RefillOrderServiceImplTest {
	
	@InjectMocks
	private RefillOrderServiceImpl refillOrderServiceImpl;
	
	@Mock
	private RefillorderSubscriptionServiceImpl refillOrderSubscriptionService;
	
	@Mock
	private AuthFeign authFeign;
	
	@Mock
	private DrugClient drugDetailClient;
	
	@Mock
	private SubscriptionClient subscriptionClient;
	
	@Mock
	private RefillOrderRepository refillOrderRepository;
	
	long mId = 101;

	boolean flag = true;
	
	@Test
	void getStatus() throws SubscriptionIDNotFoundException, InvalidTokenException {

		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1, 1, "CR7", flag, new Date(), 5);
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.findAll()).thenReturn((ArrayList<RefillOrder>) list);
		System.out.println(refillOrderServiceImpl.getStatus(1, "token"));

		List<RefillOrder> actual = (refillOrderServiceImpl.getStatus(1, "token"));

		assertEquals(true, actual.get(0).getPayStatus());

	}
	
	@Test
	public void getStatusInvalidToken() throws SubscriptionIDNotFoundException, InvalidTokenException {
		TokenValid response = new TokenValid("uid", "name", false);
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, () -> refillOrderServiceImpl.getStatus(45, "token"));
	}
	
	@Test
	public void getStatusInvalidSubscriptionIdNotFoundException()
			throws SubscriptionIDNotFoundException, InvalidTokenException {
		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1, 1, "CR7", flag, new Date(), 5);
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.findAll()).thenReturn((ArrayList<RefillOrder>) list);
		assertThrows(SubscriptionIDNotFoundException.class, () -> refillOrderServiceImpl.getStatus(54, "token"));
	}
	
	
	@Test
	public void getRefillDuesAsOfPaymentException()
			throws SubscriptionIDNotFoundException, InvalidTokenException, ParseException {
		TokenValid response = new TokenValid("uid", "name", false);
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, () -> refillOrderServiceImpl.getRefillPaymentDues(45, "token"));

	}
	@Test
	public void getRefillDuesAsOfPaymentFalse()
			throws SubscriptionIDNotFoundException, InvalidTokenException, ParseException {
		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1, 1, "CR7", true, new Date(),2);
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.findAll()).thenReturn(list);
		refillOrderServiceImpl.getRefillPaymentDues(45, "token");
		assertEquals(list.get(0).getPayStatus(), refillOrder.getPayStatus());

	}
	
	@Test
	public void getRefillDuesAsOfPaymentTrue()
			throws SubscriptionIDNotFoundException, InvalidTokenException, ParseException {
		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1, 1, "CR7", true, new Date(),2);
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true);
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.findAll()).thenReturn(list);
		refillOrderServiceImpl.getRefillPaymentDues(54, "token");

		assertEquals(list.get(0).getPayStatus(), refillOrder.getPayStatus());
	}
	
	
	
	@Test
	void requestAdhocRefilllInvalidTokenException()
			throws SubscriptionIDNotFoundException, InvalidTokenException, ParseException {
		TokenValid response = new TokenValid("uid", "name", false);
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class,
				() -> refillOrderServiceImpl.requestAdhocRefill(1, 1,"CR7","kolkata",5, "token"));

	}
	
}
