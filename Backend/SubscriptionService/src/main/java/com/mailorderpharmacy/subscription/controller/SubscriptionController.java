package com.mailorderpharmacy.subscription.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;
import com.mailorderpharmacy.subscription.entity.SubscriptionDetails;
import com.mailorderpharmacy.subscription.exceptions.InvalidTokenException;
import com.mailorderpharmacy.subscription.exceptions.PaymentUnclearException;
import com.mailorderpharmacy.subscription.exceptions.SubscriptionListEmptyException;
import com.mailorderpharmacy.subscription.services.SubscriptionService;

import feign.FeignException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(produces = "application/json", value="Manages subscriptions and unsubscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@CrossOrigin
	@ApiOperation(value = "Subscribes to the service", response = ResponseEntity.class)
	@PostMapping("/subscribe")
	public ResponseEntity<String> subscribe(@RequestHeader("Authorization") String token,
			@RequestBody PrescriptionDetails prescriptionDetails) throws InvalidTokenException {
		
		System.out.println(prescriptionDetails.toString());
		log.info("Inside subscribe controller method");
		return subscriptionService.subscribe(prescriptionDetails, token);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Unsubscribes to the service", response = ResponseEntity.class)
	@PostMapping("/unsubscribe/{mId}/{sId}")
	public ResponseEntity<String> unsubscribe(@RequestHeader("Authorization") String token,
			@PathVariable("mId") String memberId, @PathVariable("sId") Long subscriptionId)
			 throws InvalidTokenException, FeignException, PaymentUnclearException {
		try {
			log.info("Inside unsubscribe method");
			return subscriptionService.unsubscribe(memberId, subscriptionId, token);
		} catch (InvalidTokenException e){
			throw new InvalidTokenException("Token Expired");
		}
	}

	@CrossOrigin
	@ApiOperation(value = "Get list of subscriptions", response = List.class)
	@GetMapping("/getAllSubscriptions/{mId}")
	public List<SubscriptionDetails> getAllSubscriptionsforMember(@RequestHeader("Authorization") String token,
			@PathVariable("mId") String mId) throws InvalidTokenException, SubscriptionListEmptyException{
			return subscriptionService.getAllSubscriptions(mId, token);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Get name of the drug for given subscription service", response = ResponseEntity.class)
	@GetMapping("/getDrugName/{sId}")
	public ResponseEntity<String> getDrugNameBySubscriptionId(@RequestHeader("Authorization") String token,
			@PathVariable("sId") Long sId) throws InvalidTokenException{
		try {
			return subscriptionService.getDrugNameBySubscriptionId(sId, token);
		}catch (Exception e){
			throw new InvalidTokenException("Token Expired");
		}
	}
	
}
