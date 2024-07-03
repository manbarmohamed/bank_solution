package com.banksolution.ebank.service;

import com.banksolution.ebank.model.Beneficiaire;
import com.banksolution.ebank.repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaireService {
    @Autowired
    private BeneficiaireRepository beneficiaireRepository;

    public List<Beneficiaire> getAllBeneficiaire() {
        return beneficiaireRepository.findAll();
    }
    public Optional<Beneficiaire> getBeneficiaireById(Long id) {
        return beneficiaireRepository.findById(id);
    }
    public Beneficiaire save(Beneficiaire beneficiaire) {
        return beneficiaireRepository.save(beneficiaire);
    }
    public void deleteBeneficiaire(Long id) {
        beneficiaireRepository.deleteById(id);
    }
}
