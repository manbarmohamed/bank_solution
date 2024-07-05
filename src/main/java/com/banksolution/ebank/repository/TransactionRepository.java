package com.banksolution.ebank.repository;


import com.banksolution.ebank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select * FROM transaction t INNER JOIN compte c INNER JOIN utilisateur u on t.compte_id=c.id AND c.utilisateur_id=u.id", nativeQuery = true)
    List<Object> findAllByUSER();
    @Query(value = "SELECT * from transaction inner join compte c on c.id=?1",nativeQuery = true)
    List<Object> findAllByCOMPTE(Long id_compte);

}
