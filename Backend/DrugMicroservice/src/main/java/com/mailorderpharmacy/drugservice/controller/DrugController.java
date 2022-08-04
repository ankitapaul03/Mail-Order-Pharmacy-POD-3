package com.mailorderpharmacy.drugservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharmacy.drugservice.entity.DrugDetails;
import com.mailorderpharmacy.drugservice.entity.Stock;
import com.mailorderpharmacy.drugservice.entity.SuccessResponse;
import com.mailorderpharmacy.drugservice.exception.DrugNotFoundException;
import com.mailorderpharmacy.drugservice.exception.InvalidTokenException;
import com.mailorderpharmacy.drugservice.exception.StockNotFoundException;
import com.mailorderpharmacy.drugservice.service.DrugDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(produces = "application/json", value = "Manages drugs")
public class DrugController
{
    @Autowired
    DrugDetailsService drugDetailsService;
   
    @CrossOrigin
    @ApiOperation(value = "Get all drugs", response = List.class)
    @GetMapping({ "/getAllDrugs" })
    public List<DrugDetails> getAllDrugs() {
        return this.drugDetailsService.getAllDrugs();
    }

    @CrossOrigin
    @ApiOperation(value = "Search drug by id", response = DrugDetails.class)
    @GetMapping({ "/searchDrugsById/{id}" })
    public DrugDetails getDrugById(@RequestHeader("Authorization") final String token, @PathVariable("id") final String id) throws InvalidTokenException, DrugNotFoundException {
        return this.drugDetailsService.getDrugById(id, token);
    }
 
    @CrossOrigin
    @ApiOperation(value = "Search drug by name", response = DrugDetails.class)
    @GetMapping({ "/searchDrugsByName/{name}" })
    public DrugDetails getDrugByName(@RequestHeader("Authorization") final String token, @PathVariable("name") final String name) throws InvalidTokenException, DrugNotFoundException {
        return this.drugDetailsService.getDrugByName(name, token);
    }
 
    @CrossOrigin
    @ApiOperation(value = "Search stock by id", response = Stock.class)
    @GetMapping({ "/getDispatchableDrugStock/{id}/{location}" })
    public Stock getDispatchableDrugStock(@RequestHeader("Authorization") final String token, @PathVariable("id") final String id, @PathVariable("location") final String location) throws InvalidTokenException, StockNotFoundException, DrugNotFoundException {
        return this.drugDetailsService.getDispatchableDrugStock(id, location, token);
    }
    
    @CrossOrigin
    @ApiOperation(value = "Update quantity by stock", response = ResponseEntity.class )
    @PutMapping({ "/updateDispatchableDrugStock/{id}/{location}/{quantity}" })
    public ResponseEntity<SuccessResponse> updateQuantity(@RequestHeader("Authorization") final String token, @PathVariable("id") final String id, @PathVariable("location") final String location, @PathVariable("quantity") final int quantity) throws InvalidTokenException, DrugNotFoundException, StockNotFoundException {
        return this.drugDetailsService.updateQuantity(id, location, quantity, token);
    }
}