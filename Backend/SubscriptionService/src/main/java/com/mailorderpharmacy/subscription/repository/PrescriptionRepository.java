package com.mailorderpharmacy.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mailorderpharmacy.subscription.entity.PrescriptionDetails;

/**JPA Repository for Prescription which interacts with database*/

public interface PrescriptionRepository extends JpaRepository<PrescriptionDetails,Long> {

}
