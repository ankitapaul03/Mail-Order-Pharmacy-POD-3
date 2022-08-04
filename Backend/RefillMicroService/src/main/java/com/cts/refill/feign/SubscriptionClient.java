package com.cts.refill.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "http://localhost:8082/subscriptionapp", name = "Subscription-Details")
public interface SubscriptionClient {

	@GetMapping("/getDrugName/{memberID}")
	public String getDrugNameBySubscriptionId(@RequestHeader("Authorization") String token,@PathVariable("memberID") long memberID);

}
