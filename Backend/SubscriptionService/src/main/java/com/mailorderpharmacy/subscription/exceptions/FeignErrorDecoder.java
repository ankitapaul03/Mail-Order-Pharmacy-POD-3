package com.mailorderpharmacy.subscription.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.java.Log;

/** Custom exception class */

@Component
@Log
public class FeignErrorDecoder implements ErrorDecoder {
	
	private static final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);

	/* called automatically when 
	 * feign exception occurs */
	@Override
	public Exception decode(String methodKey, Response response) {

		if (response.status() == 404) {

			// when feign-client responds status code 404
			log.info("Error took place when using Feign client to send HTTP Request");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Drug is not available");
		}

		else{
			return new Exception(response.reason());
		}
	}

}