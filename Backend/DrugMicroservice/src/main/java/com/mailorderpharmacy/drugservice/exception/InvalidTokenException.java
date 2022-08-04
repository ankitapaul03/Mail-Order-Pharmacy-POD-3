package com.mailorderpharmacy.drugservice.exception;

public class InvalidTokenException extends Exception
{
    public InvalidTokenException(final String message) {
        super(message);
    }
}