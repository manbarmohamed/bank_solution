package com.banksolution.ebank.controller;


import com.banksolution.ebank.model.Transaction;
import com.banksolution.ebank.model.enums.TypeTransaction;
import com.banksolution.ebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/allByUser")
    public ResponseEntity<List<Object>> getTransactions() {
        List<Object> transactions = transactionService.getTransactionsByUser();
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/All")
    public ResponseEntity<List<Transaction>> getTransaction() {
        List<Transaction> transactionList = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionList);
    }
    @GetMapping("/AllByCompte")
    public ResponseEntity<List<Object>> getTransactionByCompte(@RequestParam Long id_Compte) {
        List<Object> transactionList = transactionService.getAllTransactionByCompte(id_Compte);
        return ResponseEntity.ok(transactionList);
    }

    @PostMapping("/add")
    public Transaction effectuerTransaction(@RequestParam Long compteId,
                                            @RequestParam Long beneficiaryId,
                                            @RequestParam double montant,
                                            @RequestParam TypeTransaction type,
                                            @RequestParam String description) throws Exception {
        return transactionService.effectuerTransaction(compteId,beneficiaryId, montant, type, description);
    }
}
