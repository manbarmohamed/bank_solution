package com.banksolution.ebank.controller;


import com.banksolution.ebank.model.CarteBancaire;
import com.banksolution.ebank.service.CarteBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carte")
public class CarteBancaireCntroller {

    @Autowired
    private CarteBancaireService carteBancaireService;

    @GetMapping("/findall")
    public ResponseEntity<List<CarteBancaire>> getCarteBancaire() {
       List<CarteBancaire> carteBancaires= carteBancaireService.findAll();
       return ResponseEntity.ok(carteBancaires);
    }
}
