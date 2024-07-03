package com.banksolution.ebank.repository;

import com.banksolution.ebank.model.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {
}
