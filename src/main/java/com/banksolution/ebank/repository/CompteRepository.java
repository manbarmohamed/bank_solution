package com.banksolution.ebank.repository;

import com.banksolution.ebank.model.Compte;
import com.banksolution.ebank.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Long> {

    List<Compte> findAllByUtilisateurIs(Utilisateur utilisateur);
}
