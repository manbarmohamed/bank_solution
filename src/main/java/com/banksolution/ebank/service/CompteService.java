package com.banksolution.ebank.service;

import com.banksolution.ebank.exception.UtilisateurNotFoundException;
import com.banksolution.ebank.model.CarteBancaire;
import com.banksolution.ebank.model.Compte;
import com.banksolution.ebank.model.enums.TypeCarte;
import com.banksolution.ebank.model.enums.TypeCompte;
import com.banksolution.ebank.repository.CarteBancaireRepository;
import com.banksolution.ebank.repository.CompteRepository;
import com.banksolution.ebank.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private CarteBancaireRepository carteBancaireRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Compte creerCompteEtCarte(Long utilisateurId, TypeCompte typeCompte, double soldeInitial) {

        Compte compte = new Compte();
        compte.setType(typeCompte);
        compte.setSolde(soldeInitial);
        compte.setDateCreation(LocalDate.now());
        compte.setActif(true);

        compte.setUtilisateur(utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé")));
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

    public void closeCompte(Long compteId) {
        compteRepository.deleteById(compteId);
    }
}
