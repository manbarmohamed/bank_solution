package com.banksolution.ebank.controller;


import com.banksolution.ebank.model.Utilisateur;
import com.banksolution.ebank.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("all")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("findbyid")
    public ResponseEntity<Utilisateur> getUtilisateurById(@RequestParam Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);

            return ResponseEntity.ok(utilisateur);

    }
}
