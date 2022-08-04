package com.cts.refill.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.refill.exception.DrugQuantityNotAvailable;
import com.cts.refill.exception.InvalidTokenException;
import com.cts.refill.exception.SubscriptionIDNotFoundException;
import com.cts.refill.model.RefillOrder;
import com.cts.refill.model.RefillOrderLine;

import feign.FeignException;


@Service
public interface IRefillOrder {
	
	public List<RefillOrder> getStatus(long subID,String token) throws SubscriptionIDNotFoundException, InvalidTokenException;

	public List<RefillOrderLine> getRefillDuesAsOfDate(String memberId, int date,String token) throws InvalidTokenException;
	
	public RefillOrder requestAdhocRefill(long policyId, long subID, String memberID, String location,int quantity,String token) throws ParseException, DrugQuantityNotAvailable, InvalidTokenException,FeignException;

	public boolean getRefillPaymentDues(long subID,String token) throws InvalidTokenException;
	
}
