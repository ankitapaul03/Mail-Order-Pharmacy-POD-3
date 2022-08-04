package com.mailorderpharmacy.drugservice.exception;

public class StockNotFoundException extends Exception
{
    public StockNotFoundException(final String message) {
        super(message);
    }
}