package com.banksolution.ebank.service;

import com.banksolution.ebank.model.Transaction;
import com.banksolution.ebank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public List<Object> getTransactionsByUser(){
        return transactionRepository.findAllByUSER();
    }

    public List<Object> getAllTransactionByCompte(Long id_Compte) {
        return transactionRepository.findAllByCOMPTE(id_Compte);
    }


}
