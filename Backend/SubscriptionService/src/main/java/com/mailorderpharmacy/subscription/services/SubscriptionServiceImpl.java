package com.mailorderpharmacy.subscription.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mailorderpharmacy.subscription.clients.AuthFeign;
import com.mailorderpharmacy.subscription.clients.DrugDetailClient;
import com.mailorderpharmacy.subscription.clients.RefillClient;
import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;
import com.mailorderpharmacy.subscription.entity.SubscriptionDetails;
import com.mailorderpharmacy.subscription.entity.TokenValid;
import com.mailorderpharmacy.subscription.exceptions.InvalidTokenException;
import com.mailorderpharmacy.subscription.exceptions.PaymentUnclearException;
import com.mailorderpharmacy.subscription.exceptions.SubscriptionListEmptyException;
import com.mailorderpharmacy.subscription.repository.PrescriptionRepository;
import com.mailorderpharmacy.subscription.repository.SubscriptionRepository;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	@Autowired
	private DrugDetailClient drugDetailClient;
	
	@Autowired
	private RefillClient refillClient;

	@Autowired
	private AuthFeign authFeign;

	@Autowired
	PrescriptionRepository prescriptionRepo;
	
	@Autowired
	SubscriptionRepository subscriptionRepo;


	String msg = "Invalid Credentials";

	@Override
	public ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetail, String token)
			throws InvalidTokenException, FeignException {
		log.info("Inside subscribe service method");
		if(authFeign.getValidity(token).getBody() !=null) {
			TokenValid tokenValid = authFeign.getValidity(token).getBody();
			if (tokenValid != null && tokenValid.isValid()) {
				log.info("subscribe service method- token is valid");
//				drugDetailClient.getDrugByName(token, prescriptionDetail.getDrugName());
				PrescriptionDetails prescriptionDetails = prescriptionRepo.save(prescriptionDetail);
				log.info("prescription saved");
				SubscriptionDetails subscriptionDetail = new SubscriptionDetails(prescriptionDetails.getPrescriptionId(),
						prescriptionDetails.getCourseDuration(), prescriptionDetails.getQuantity(),
						prescriptionDetails.getMemberId(), LocalDate.now(), prescriptionDetails.getMemberLocation(),
						"active", prescriptionDetails.getDrugName());

				log.info("subs obj created");

				SubscriptionDetails subscriptionDetails = subscriptionRepo.save(subscriptionDetail);
				// inform refill service to start refilling for this subscription
				refillClient.requestRefillSubscription(token, subscriptionDetails.getSubscriptionId(),
						subscriptionDetails.getMemberId(), subscriptionDetails.getQuantity(),
						subscriptionDetails.getRefillCycle());

				log.info("subs obj saved");
			} else
				throw new InvalidTokenException(msg);
		}

		return new ResponseEntity<>("You have succesfully subscribed to " + prescriptionDetail.getDrugName(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> unsubscribe(String mId, Long sId, String token)
			throws InvalidTokenException, FeignException, PaymentUnclearException {

		if(authFeign.getValidity(token).getBody() !=null) {
			TokenValid tokenValid = authFeign.getValidity(token).getBody();
			if (tokenValid != null && tokenValid.isValid()) {
				if (refillClient.isPendingPaymentDues(token, sId)) {
					log.info("payment not clear");
					throw new PaymentUnclearException("You have to clear your payment dues first.");
//					return new ResponseEntity<>("You have to clear your payment dues first.", HttpStatus.OK);
				}
				subscriptionRepo.deleteById(sId);
				log.info("deleted ");
				// inform refill service to stop refilling for this sId
				refillClient.deleteRefillData(token, sId);
				log.info("delete refill success");
			} else
				throw new InvalidTokenException(msg);
		}

		return new ResponseEntity<>("You have succesfully Unsubscribed", HttpStatus.OK);
	}

	@Override
	public List<SubscriptionDetails> getAllSubscriptions(String mId, String token)
			throws InvalidTokenException, SubscriptionListEmptyException {
		// gets a list  of subscriptions for a given member id
		log.info("get subscription for ");

			if (authFeign.getValidity(token).getBody().isValid()) {
				if (subscriptionRepo.findByMemberId(mId).isEmpty())
					throw new SubscriptionListEmptyException("Currently you do not have any subscriptions");
				return subscriptionRepo.findByMemberId(mId);
			} else
				throw new InvalidTokenException(msg);


	}

	@Override
	public ResponseEntity<String> getDrugNameBySubscriptionId(Long sId, String token) throws InvalidTokenException, SubscriptionListEmptyException {
		// extracts Drug name for given subscription Id
		log.info("getDrugNameBySubscriptionId");
		String drugName = null;
		if(authFeign.getValidity(token).getBody() !=null) {
			TokenValid tokenValid = authFeign.getValidity(token).getBody();
			if (tokenValid != null && tokenValid.isValid()) {
				drugName = subscriptionRepo.findById(sId)
						.orElseThrow(() -> new SubscriptionListEmptyException("DrugNotFound")).getDrugName();
			}
			else
				throw new InvalidTokenException(msg);
		}

		return new ResponseEntity<>(drugName, HttpStatus.OK);
	}
	
}
