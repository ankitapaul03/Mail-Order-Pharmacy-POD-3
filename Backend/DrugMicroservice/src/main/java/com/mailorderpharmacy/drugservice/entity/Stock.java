package com.mailorderpharmacy.drugservice.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock
{
    private String drugId;
    private String drugName;
    private Date expiryDate;
    private int stock;
    
}