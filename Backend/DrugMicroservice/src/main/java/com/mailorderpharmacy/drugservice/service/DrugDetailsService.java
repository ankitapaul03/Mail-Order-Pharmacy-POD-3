package com.mailorderpharmacy.drugservice.service;

import java.util.List;
import com.mailorderpharmacy.drugservice.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import com.mailorderpharmacy.drugservice.exception.StockNotFoundException;
import com.mailorderpharmacy.drugservice.entity.Stock;
import com.mailorderpharmacy.drugservice.exception.DrugNotFoundException;
import com.mailorderpharmacy.drugservice.exception.InvalidTokenException;
import com.mailorderpharmacy.drugservice.entity.DrugDetails;

public interface DrugDetailsService
{
    DrugDetails getDrugById(final String id, final String token) throws InvalidTokenException, DrugNotFoundException;
    
    DrugDetails getDrugByName(final String name, final String token) throws InvalidTokenException, DrugNotFoundException;
    
    Stock getDispatchableDrugStock(final String id, final String location, final String token) throws InvalidTokenException, StockNotFoundException, DrugNotFoundException;
    
    ResponseEntity<SuccessResponse> updateQuantity(final String id, final String location, final int quantity, final String token) throws InvalidTokenException, DrugNotFoundException, StockNotFoundException;
    
    List<DrugDetails> getAllDrugs();
}