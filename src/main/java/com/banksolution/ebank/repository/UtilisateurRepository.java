package com.banksolution.ebank.repository;

import com.banksolution.ebank.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByEmail(String userName);

}
