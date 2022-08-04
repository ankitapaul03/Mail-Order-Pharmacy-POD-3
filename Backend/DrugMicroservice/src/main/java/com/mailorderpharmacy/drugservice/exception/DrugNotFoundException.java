package com.mailorderpharmacy.drugservice.exception;

public class DrugNotFoundException extends RuntimeException
{
    public DrugNotFoundException(final String message) {
        super(message);
    }
}