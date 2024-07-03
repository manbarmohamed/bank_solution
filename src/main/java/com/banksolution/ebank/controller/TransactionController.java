package com.banksolution.ebank.controller;


import com.banksolution.ebank.model.Transaction;
import com.banksolution.ebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/allbyuser")
    public ResponseEntity<List<Object>> getTransactions() {
        List<Object> transactions = transactionService.getTransactionsByUser();
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/All")
    public ResponseEntity<List<Transaction>> getTransaction() {
        List<Transaction> transactionList = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionList);
    }
}
