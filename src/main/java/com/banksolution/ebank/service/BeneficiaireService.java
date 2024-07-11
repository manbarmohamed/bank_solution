package com.banksolution.ebank.service;


import com.banksolution.ebank.model.Beneficiaire;
import com.banksolution.ebank.repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service pour gérer les opérations sur les bénéficiaires.
 */
@Service
public class BeneficiaireService {

    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    /**
     * Récupère la liste de tous les bénéficiaires.
     *
     * @return une liste de tous les bénéficiaires.
     */
    public List<Beneficiaire> getAllBeneficiaire() {
        return beneficiaireRepository.findAll();
    }

    /**
     * Récupère un bénéficiaire par son identifiant.
     *
     * @param id l'identifiant du bénéficiaire.
     * @return le bénéficiaire correspondant à l'identifiant.
     */
    public Beneficiaire getBeneficiaireById(Long id) {
        return beneficiaireRepository.findById(id).get();
    }

    /**
     * Enregistre un nouveau bénéficiaire ou met à jour un bénéficiaire existant.
     *
     * @param beneficiaire le bénéficiaire à enregistrer ou à mettre à jour.
     * @return le bénéficiaire enregistré ou mis à jour.
     */
    public Beneficiaire save(Beneficiaire beneficiaire) {
        return beneficiaireRepository.save(beneficiaire);
    }

    /**
     * Supprime un bénéficiaire par son identifiant.
     *
     * @param id l'identifiant du bénéficiaire à supprimer.
     */
    public void deleteBeneficiaire(Long id) {
        beneficiaireRepository.deleteById(id);
    }

    /**
     * Met à jour les informations d'un bénéficiaire existant.
     *
     * @param beneficiaire le bénéficiaire avec les informations mises à jour.
     * @return le bénéficiaire mis à jour.
     */
    public Beneficiaire updateBeneficiaire(Beneficiaire beneficiaire) {
        return beneficiaireRepository.save(beneficiaire);
    }
}
