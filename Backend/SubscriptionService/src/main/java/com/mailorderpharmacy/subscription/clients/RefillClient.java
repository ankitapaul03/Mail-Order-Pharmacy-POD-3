package com.mailorderpharmacy.subscription.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "http://localhost:8454/refillapp", name = "refillapp")
public interface RefillClient {

	@GetMapping("/getRefillPaymentDues/{sub_id}")
	public Boolean isPendingPaymentDues(@RequestHeader("Authorization") final String token,
			@PathVariable("sub_id") Long sId);


	@PostMapping("/requestRefillSubscription/{sub_id}/{memberId}/{quantity}/{refillCycle}")
	public ResponseEntity<Object> requestRefillSubscription(@RequestHeader("Authorization") final String token,
			@PathVariable("sub_id") Long subId, @PathVariable("memberId") String memberId,
			@PathVariable("quantity") int quantity, @PathVariable("refillCycle") int refillCycle);


	@DeleteMapping("/deleteBySubscriptionId/{subscriptionId}")
	public void deleteRefillData(@RequestHeader("Authorization") final String token,
			@PathVariable("subscriptionId") Long subscriptionId);
}
