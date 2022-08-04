package com.mailorderpharmacy.subscription.entitytest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mailorderpharmacy.subscription.entity.DrugDetails;
import com.mailorderpharmacy.subscription.entity.DrugLocationDetails;

import lombok.Data;


 class DrugLocationDetailsTest {
	DrugDetails drugDetails = new DrugDetails();
	DrugLocationDetails details1 = new DrugLocationDetails();
	DrugLocationDetails details2 = new DrugLocationDetails("ABC","Chennai",25,drugDetails);

	@Test
	void testSerialId() {
		details1.setSerialId("ABC");
		assertEquals("ABC", details2.getSerialId());
	}
	
	@Test
	void testLocation() {
		details1.setLocation("Chennai");
		assertEquals("Chennai",details2.getLocation());
	}
	
	@Test
	void testQuantity() {
		details1.setQuantity(25);
		assertEquals(25,details2.getQuantity());
	}
	
	@Test
	void testDrugList() {
		details1.setDrugDetails(drugDetails);
		assertEquals(drugDetails,details2.getDrugDetails());
	}
}

