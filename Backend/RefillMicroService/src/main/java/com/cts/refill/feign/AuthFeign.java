package com.cts.refill.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.refill.model.TokenValid;

@FeignClient(name = "AUTH", url = "http://localhost:8090/authapp")
public interface AuthFeign {

	@GetMapping(value = "/validate")
	public TokenValid getValidity(@RequestHeader("Authorization") final String token);
}
