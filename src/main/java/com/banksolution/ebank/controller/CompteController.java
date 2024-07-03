package com.banksolution.ebank.controller;

import com.banksolution.ebank.model.Compte;
import com.banksolution.ebank.model.enums.TypeCompte;
import com.banksolution.ebank.service.CompteService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compte")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @PostMapping("/creer")
    public ResponseEntity<Compte> creerCompte(@RequestParam Long utilisateurId, @RequestParam TypeCompte typeCompte, @RequestParam double soldeInitial) {
        Compte compte = compteService.creerCompteEtCarte(utilisateurId, typeCompte, soldeInitial);
        return ResponseEntity.ok(compte);
    }
    @GetMapping("/findall")
    public ResponseEntity<List<Compte>> findAllCompte() {
        List<Compte> comptes = compteService.getComptes();
        return ResponseEntity.ok(comptes);
    }
}
