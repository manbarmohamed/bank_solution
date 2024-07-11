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

/**
 * Service pour gérer les opérations sur les transactions.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    /**
     * Récupère la liste de toutes les transactions.
     *
     * @return une liste de toutes les transactions.
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Récupère les transactions associées à un utilisateur.
     *
     * @return une liste d'objets représentant les transactions par utilisateur.
     */
    public List<Object> getTransactionsByUser() {
        return transactionRepository.findAllByUSER();
    }

    /**
     * Récupère toutes les transactions associées à un compte.
     *
     * @param id_Compte l'identifiant du compte.
     * @return une liste d'objets représentant les transactions par compte.
     */
    public List<Object> getAllTransactionByCompte(Long id_Compte) {
        return transactionRepository.findAllByCOMPTE(id_Compte);
    }

    /**
     * Effectue une transaction sur un compte donné.
     *
     * @param compteId l'identifiant du compte.
     * @param benefId l'identifiant du bénéficiaire.
     * @param montant le montant de la transaction.
     * @param type le type de transaction (DEBIT ou CREDIT).
     * @param description la description de la transaction.
     * @return la transaction effectuée.
     * @throws Exception si le compte ou le bénéficiaire n'est pas trouvé ou si le solde est insuffisant pour un débit.
     */
    @Transactional
    public Transaction effectuerTransaction(Long compteId, Long benefId, double montant, TypeTransaction type, String description)  {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new CompteNotFoundException("Compte non trouvé"));
        Beneficiaire beneficiaire = beneficiaireRepository.findById(benefId)
                .orElseThrow(() -> new BeneficiaireNotFoundException("Bénéficiaire non trouvé"));

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
