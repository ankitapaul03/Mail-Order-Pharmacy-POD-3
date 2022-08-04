package com.mailorderpharmacy.subscription.entitytest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.junit.jupiter.api.Test;

import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;


 class PrescriptionDetailsTest {

	LocalDate date = LocalDate.now();  
	 
	 PrescriptionDetails prescriptionDetails =new PrescriptionDetails((long) 45,"member","salem","45","45"
				,date,"member","member",45,45,"member");
	 PrescriptionDetails prescriptionDetails1 =new PrescriptionDetails();
	 
	 	@Test
		 void prescriptionDetailssetTest()
		{
	 		prescriptionDetails1.setMemberId("member");
	 		prescriptionDetails1.setCourseDuration(45);
	 		prescriptionDetails1.setDoctorName("member");
	 		prescriptionDetails1.setDosageDefinition("member");
	 		prescriptionDetails1.setDrugName("member");
	 		prescriptionDetails1.setInsuranceProvider("member");
	 		prescriptionDetails1.setMemberLocation("salem");
	 		prescriptionDetails1.setPolicyNumber("45"); 
	 		prescriptionDetails1.setPrescriptionDate(date);
	 		prescriptionDetails1.setPrescriptionId((long) 45);
	 		prescriptionDetails1.setQuantity(45);
	 		assertEquals(45, prescriptionDetails1.getCourseDuration());
	 		assertEquals("member", prescriptionDetails1.getDoctorName());
	 		assertEquals("member", prescriptionDetails1.getDosageDefinition());
	 		assertEquals("member", prescriptionDetails1.getDrugName());
	 		assertEquals("member", prescriptionDetails1.getInsuranceProvider());
	 		assertEquals("salem", prescriptionDetails1.getMemberLocation());
	 		assertEquals("45", prescriptionDetails1.getPolicyNumber());
	 		assertEquals(date, prescriptionDetails1.getPrescriptionDate());
	 		assertEquals((long)45, prescriptionDetails1.getPrescriptionId());
	 		assertEquals(45, prescriptionDetails1.getQuantity());
	 		assertEquals("member", prescriptionDetails1.getMemberId());
	 		
		}

}
