package com.mailorderpharmacy.drugservice.dao;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.mailorderpharmacy.drugservice.entity.DrugDetails;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DrugDetailsRepository extends JpaRepository<DrugDetails, String>
{
    Optional<DrugDetails> findById(final String id);
    
    Optional<DrugDetails> findBydrugName(final String name);
}