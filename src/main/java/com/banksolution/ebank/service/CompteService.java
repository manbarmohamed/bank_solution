package com.banksolution.ebank.service;

import com.banksolution.ebank.exception.CarteBancaireNotFoundException;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service pour gérer les opérations sur les comptes bancaires.
 */
@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private CarteBancaireRepository carteBancaireRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Crée un nouveau compte et une carte bancaire pour un utilisateur.
     *
     * @param utilisateurId l'identifiant de l'utilisateur.
     * @param typeCompte le type de compte à créer.
     * @param soldeInitial le solde initial du compte.
     * @return le compte créé.
     * @throws Exception si l'utilisateur n'est pas trouvé ou si un compte de ce type existe déjà pour cet utilisateur.
     */
    public Compte creerCompteEtCarte(Long utilisateurId, TypeCompte typeCompte, double soldeInitial) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException("Utilisateur non trouvé"));

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

    /**
     * Récupère la liste de tous les comptes.
     *
     * @return une liste de tous les comptes.
     */
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }

    /**
     * Récupère la liste des comptes associés à un utilisateur donné.
     *
     * @param utilisateur l'utilisateur pour lequel récupérer les comptes.
     * @return une liste de comptes associés à l'utilisateur.
     */
    public List<Compte> getComptesByUser(Utilisateur utilisateur) {
        return compteRepository.findAllByUtilisateurIs(utilisateur);
    }

    /**
     * Récupère un compte par son identifiant.
     *
     * @param id l'identifiant du compte.
     * @return le compte correspondant à l'identifiant.
     */
    public Compte getCompteById(Long id) {
        return compteRepository.findById(id).get();
    }

    /**
     * Supprime un compte s'il est trouvé et que son solde est égal à zéro.
     *
     * @param compte le compte à supprimer.
     * @throws Exception si le compte n'est pas trouvé ou si son solde n'est pas zéro.
     */
    public void deleteAccount(Compte compte) throws Exception {
        Compte compteClosed = compteRepository.findById(compte.getId())
                .orElseThrow(() -> new CarteBancaireNotFoundException("Not found!!"));
        if (compteClosed.getSolde() != 0) {
            throw new Exception("Account must be zero to close!!");
        }
        compteRepository.delete(compte);
    }
}
