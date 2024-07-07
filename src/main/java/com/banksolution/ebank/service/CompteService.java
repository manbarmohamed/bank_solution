package com.banksolution.ebank.service;

import com.banksolution.ebank.exception.UtilisateurNotFoundException;
import com.banksolution.ebank.model.CarteBancaire;
import com.banksolution.ebank.model.Compte;
import com.banksolution.ebank.model.Utilisateur;
import com.banksolution.ebank.model.enums.TypeCarte;
import com.banksolution.ebank.model.enums.TypeCompte;
import com.banksolution.ebank.repository.CarteBancaireRepository;
import com.banksolution.ebank.repository.CompteRepository;
import com.banksolution.ebank.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private CarteBancaireRepository carteBancaireRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Compte creerCompteEtCarte(Long utilisateurId, TypeCompte typeCompte, double soldeInitial) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));


        boolean compteExiste = utilisateur.getComptes().stream()
                .anyMatch(compte -> compte.getType().equals(typeCompte));
        if (compteExiste) {
            throw new IllegalArgumentException("Un compte de ce type existe déjà pour cet utilisateur.");
        }

        Compte compte = new Compte();
        compte.setType(typeCompte);
        compte.setSolde(soldeInitial);
        compte.setDateCreation(LocalDate.now());
        compte.setActif(true);
        compte.setUtilisateur(utilisateur);
        compteRepository.save(compte);


        CarteBancaire carteBancaire = new CarteBancaire();
        carteBancaire.setNumero(UUID.randomUUID().toString());
        carteBancaire.setDateExpiration(LocalDate.now().plusYears(4));
        carteBancaire.setType(TypeCarte.DEBIT);
        carteBancaire.setActive(true);
        carteBancaire.setBloquee(false);
        carteBancaire.setCompte(compte);
        carteBancaireRepository.save(carteBancaire);

        return compte;
    }

    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }


    public List<Compte> getComptesByUser(Utilisateur utilisateur){
        return compteRepository.findAllByUtilisateurIs(utilisateur);
    }

    public Compte getCompteById(Long id) {
        return compteRepository.findById(id).get();
    }

    public void deleteAccount(Compte compte) throws Exception {
        Compte compteClosed = compteRepository.findById(compte.getId()).orElseThrow(()-> new Exception("Not found!!"));
        if (compteClosed.getSolde()!=0){
            throw new Exception("Account be zero to close!!");
        }
        compteRepository.delete(compte);
    }
}
