package com.banksolution.ebank.repository;

import com.banksolution.ebank.model.CarteBancaire;
import com.banksolution.ebank.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Long> {

    List<CarteBancaire> findByCompteId(Long compte_id);
}
