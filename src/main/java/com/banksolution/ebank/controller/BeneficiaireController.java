package com.banksolution.ebank.controller;


import com.banksolution.ebank.model.Beneficiaire;
import com.banksolution.ebank.service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
