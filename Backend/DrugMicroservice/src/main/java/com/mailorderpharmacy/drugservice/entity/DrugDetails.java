package com.mailorderpharmacy.drugservice.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDetails
{
    @Id
    private String drugId;
    private String drugName;
    private String manufacturer;
    private Date manufactureDate;
    private Date expiryDate;
    @OneToMany(mappedBy = "drugDetails")
    private List<DrugLocationDetails> druglocationQuantities;
    
}