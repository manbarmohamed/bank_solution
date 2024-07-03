package com.banksolution.ebank.repository;

import com.banksolution.ebank.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
