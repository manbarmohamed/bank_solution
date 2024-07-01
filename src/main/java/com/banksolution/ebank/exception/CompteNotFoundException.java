package com.banksolution.ebank.exception;

public class CompteNotFoundException extends RuntimeException {
    public CompteNotFoundException(String message) {
        super(message);
    }
}
