package com.banksolution.ebank.repository;


import com.banksolution.ebank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select * FROM transaction t INNER JOIN compte c INNER JOIN utilisateur u on t.compte_id=c.id AND c.utilisateur_id=u.id", nativeQuery = true)
    List<Object> findAllByUSER();
}
