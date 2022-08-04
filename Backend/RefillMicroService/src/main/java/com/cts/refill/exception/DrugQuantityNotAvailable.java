package com.cts.refill.exception;

@SuppressWarnings("serial")
public class DrugQuantityNotAvailable extends Exception {
	
	/**
	 * @param message
	 */
	public  DrugQuantityNotAvailable(String message) {
		super(message);
	}
			
}
