package com.banksolution.ebank.controller;

import com.banksolution.ebank.model.Compte;
import com.banksolution.ebank.model.Utilisateur;
import com.banksolution.ebank.model.enums.TypeCompte;
import com.banksolution.ebank.service.CompteService;
import com.banksolution.ebank.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compte")
public class CompteController {

    @Autowired
    private CompteService compteService;
    @Autowired
    private UtilisateurService utilisateurService;

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
    @GetMapping("/findByUser")
    public ResponseEntity<List<Compte>> findByUser(@RequestParam Long utilisateurId) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(utilisateurId);
        List<Compte> comptes = compteService.getComptesByUser(utilisateur);
        return ResponseEntity.ok(comptes);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Compte> findById(@PathVariable Long id) {
        Compte compte = compteService.getCompteById(id);
        return ResponseEntity.ok(compte);
    }

    @DeleteMapping("close/{id}")
    public void closeAccount(@PathVariable Long id) throws Exception {
        Compte compte = compteService.getCompteById(id);
        System.out.println(compte);
        compteService.deleteAccount(compte);
    }
}
