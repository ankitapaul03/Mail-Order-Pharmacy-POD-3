package com.mailorderpharmacy.subscription.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mailorderpharmacy.subscription.entity.DrugDetails;

@FeignClient(url="http://localhost:8081/drugdetailapp",name="drugdetailapp")
public interface DrugDetailClient {

	@GetMapping("/searchDrugsByName/{name}")
	public DrugDetails getDrugByName(@RequestHeader("Authorization") final String token, @PathVariable String name);
}
