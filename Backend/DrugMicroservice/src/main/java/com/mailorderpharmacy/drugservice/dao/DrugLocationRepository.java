package com.mailorderpharmacy.drugservice.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mailorderpharmacy.drugservice.entity.DrugLocationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DrugLocationRepository extends JpaRepository<DrugLocationDetails, String>
{
    List<DrugLocationDetails> findByserialId(final String string);
}