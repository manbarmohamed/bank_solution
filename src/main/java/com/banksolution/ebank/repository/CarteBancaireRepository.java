package com.banksolution.ebank.repository;

import com.banksolution.ebank.model.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Long> {
}
