package com.banksolution.ebank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompteNotFoundException.class)
    public ResponseEntity<String> handleCompteNotFoundException(CompteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SoldeInsuffisantException.class)
    public ResponseEntity<String> handleSoldeInsuffisantException(SoldeInsuffisantException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CarteBancaireNotFoundException.class)
    public ResponseEntity<String> handleCarteBancaireNotFoundException(CarteBancaireNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BeneficiaireNotFoundException.class)
    public ResponseEntity<String> handleBeneficiaireNotFoundException(BeneficiaireNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur inattendue s'est produite.");
    }
}