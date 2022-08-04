package com.cts.refill.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.refill.exception.DrugQuantityNotAvailable;
import com.cts.refill.exception.InvalidTokenException;
import com.cts.refill.exception.SubscriptionIDNotFoundException;
import com.cts.refill.feign.AuthFeign;
import com.cts.refill.feign.DrugClient;
import com.cts.refill.feign.SubscriptionClient;
import com.cts.refill.model.RefillOrder;
import com.cts.refill.model.RefillOrderLine;
import com.cts.refill.repo.RefillOrderRepository;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class RefillOrderServiceImpl implements IRefillOrder {

	@Autowired
	private RefillOrderRepository orepo;

	@Autowired
	private IRefillOrderSubscription subservice;

	@Autowired
	private SubscriptionClient subClient;

	@Autowired
	private DrugClient drugClient;
	
	@Autowired
	private AuthFeign authFeign;
	
	@Value("${Exception.message}")
	private String msg;

	@Override
	public List<RefillOrder> getStatus(long subID,String token) throws SubscriptionIDNotFoundException, com.cts.refill.exception.InvalidTokenException {
		log.info("Inside getStatus");
		if (authFeign.getValidity(token).isValid()) {
			List<RefillOrder> list = new ArrayList<>();
			List<RefillOrder> flist = null;
			try {
				list = orepo.findAll();
				flist = list.stream().filter(x -> x.getSubID() == subID).collect(Collectors.toList());
				flist.get(0);
			} catch (Exception e) {
				throw new SubscriptionIDNotFoundException("Invalid Subscription ID");
			}
			return flist;
		}
		else
			throw new InvalidTokenException(msg);
		

	}
	
	

	@Override
	public List<RefillOrderLine> getRefillDuesAsOfDate(String memberId, int date,String token) throws InvalidTokenException {
		log.info("Inside getRefillDuesAsOfDate");
		if (authFeign.getValidity(token).isValid()) {
			List<RefillOrderLine> list = subservice.getAllSubscription(token);
			List<RefillOrderLine> memberList = list.stream().filter(x -> x.getMemberID().equals(memberId))
					.collect(Collectors.toList());
			log.info(Integer.toString(date));
			return memberList.stream().filter(x -> date % x.getRefillTime() != 0).collect(Collectors.toList());
		}
		else
			throw new InvalidTokenException(msg);
		}

	@Override
	public RefillOrder requestAdhocRefill(long policyId, long subID, String memberID, String location,int quantity,String token)
			throws ParseException, DrugQuantityNotAvailable, InvalidTokenException,FeignException {
		log.info("inside requestAdhocRefill");
		
		if (authFeign.getValidity(token).isValid()) {
			String drugrName = subClient.getDrugNameBySubscriptionId(token,subID);
			String name = drugrName;
			ResponseEntity<?> updateDetail = drugClient.updateQuantity(token,name, location, quantity);
			int responseValue = updateDetail.getStatusCodeValue();
			if (responseValue == 200) {
				RefillOrder order = new RefillOrder();
				order.setSubID(subID);
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy  hh:mm:ss");
				String str = format.format(date);
				order.setRefillDate(format.parse(str));
				order.setQuantity(quantity);
				order.setMemberID(memberID);
				order.setPayStatus(true);
				orepo.save(order);
				return order;
			} else {
				throw new DrugQuantityNotAvailable("Drug Quantity Not Available");
			}
		}
		else
			throw new InvalidTokenException(msg);
		

	}

	
	
	@Override
	public boolean getRefillPaymentDues(long subID,String token) throws InvalidTokenException {
		log.info("inside getRefillDuesAsOfPayment");
		if (authFeign.getValidity(token).isValid()) {
			List<RefillOrder> list = orepo.findAll();
			List<RefillOrder> subList = list.stream().filter(x -> x.getSubID() == subID).collect(Collectors.toList());
			if (subList.isEmpty())
				return true;
			else {
				List<RefillOrder> paymentDue = subList.stream().filter(x -> (!x.getPayStatus()))
						.collect(Collectors.toList());
				if (paymentDue.isEmpty())
					return false;
			}

			return true;
		}
		else
			throw new InvalidTokenException(msg);
		
	}

}
