package com.banksolution.ebank.service;

import com.banksolution.ebank.model.Utilisateur;
import com.banksolution.ebank.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les opérations sur les utilisateurs.
 */
@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return une liste de tous les utilisateurs.
     */
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur.
     * @return l'utilisateur correspondant à l'identifiant.
     */
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }
}
