package com.mailorderpharmacy.drugservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenValid
{
    private String uid;
    private String name;
    private boolean isValid;

}