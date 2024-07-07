package com.banksolution.ebank.service;

import com.banksolution.ebank.exception.BeneficiaireNotFoundException;
import com.banksolution.ebank.exception.CompteNotFoundException;
import com.banksolution.ebank.exception.InsufficientBalanceException;
import com.banksolution.ebank.model.Beneficiaire;
import com.banksolution.ebank.model.Compte;
import com.banksolution.ebank.model.Transaction;
import com.banksolution.ebank.model.enums.TypeTransaction;
import com.banksolution.ebank.repository.BeneficiaireRepository;
import com.banksolution.ebank.repository.CompteRepository;
import com.banksolution.ebank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public List<Object> getTransactionsByUser(){
        return transactionRepository.findAllByUSER();
    }

    public List<Object> getAllTransactionByCompte(Long id_Compte) {
        return transactionRepository.findAllByCOMPTE(id_Compte);
    }


    @Transactional
    public Transaction effectuerTransaction(Long compteId, Long benefId,double montant, TypeTransaction type, String description) throws Exception {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte non trouvé"));
        Beneficiaire beneficiaire = beneficiaireRepository.findById(benefId).orElseThrow(() -> new BeneficiaireNotFoundException("Compte non trouvé"));

        if (type == TypeTransaction.DEBIT && compte.getSolde() < montant) {
            throw new InsufficientBalanceException("Solde insuffisant pour effectuer cette transaction");
        }


        if (type == TypeTransaction.DEBIT) {
            compte.setSolde(compte.getSolde() - montant);
        } else if (type == TypeTransaction.CREDIT) {
            compte.setSolde(compte.getSolde() + montant);
        }
        compteRepository.save(compte);


        Transaction transaction = new Transaction();
        transaction.setDateHeure(LocalDateTime.now());
        transaction.setMontant(montant);
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setCompte(compte);
        transaction.setBeneficiaire(beneficiaire);
        return transactionRepository.save(transaction);
    }


}
