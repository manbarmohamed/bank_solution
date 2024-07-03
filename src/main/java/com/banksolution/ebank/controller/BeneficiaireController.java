package com.banksolution.ebank.controller;



import com.banksolution.ebank.model.Beneficiaire;
import com.banksolution.ebank.service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/beneficiaire")
public class BeneficiaireController {

    @Autowired
    private BeneficiaireService beneficiaireService;
    @GetMapping("/all")
    public ResponseEntity<List<Beneficiaire>> getBeneficiaire() {
        List<Beneficiaire> beneficiaireList = beneficiaireService.getAllBeneficiaire();
        return ResponseEntity.ok(beneficiaireList);
    }
    @DeleteMapping("/del/{id}")
    public String deleteBeneficiaire(@PathVariable Long id) {
        beneficiaireService.deleteBeneficiaire(id);
        return "Beneficiaire deleted";
    }
    @GetMapping("/finsById/{id}")
    public Beneficiaire getBeneficiaireById(@PathVariable Long id) {
        return beneficiaireService.getBeneficiaireById(id);
    }
    @PostMapping("/add")
    public Beneficiaire saveBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        return beneficiaireService.save(beneficiaire);
    }

    @PutMapping("/edit/{id}")
    public String updateBeneficiain(@RequestBody Beneficiaire beneficiaire, @PathVariable Long id) {
        Beneficiaire beneficiaireUpdated = beneficiaireService.getBeneficiaireById(id);
        beneficiaireUpdated.setNom(beneficiaire.getNom());
        beneficiaireUpdated.setBanque(beneficiaire.getBanque());
        beneficiaireUpdated.setNumeroCompte(beneficiaire.getNumeroCompte());
        beneficiaireService.updateBeneficiaire(beneficiaireUpdated);
        return "Beneficiaire updated";
    }

}
