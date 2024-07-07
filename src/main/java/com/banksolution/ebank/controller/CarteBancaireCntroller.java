package com.banksolution.ebank.controller;


import com.banksolution.ebank.model.CarteBancaire;
import com.banksolution.ebank.service.CarteBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{accountId}")
    public List<CarteBancaire> getCardsByAccountId(@PathVariable Long accountId) {
        return carteBancaireService.findByCompteId(accountId);
    }

    @PostMapping("/Add")
    public CarteBancaire addCarte(@RequestParam Long accountID,@RequestBody CarteBancaire carteBancaire) {
        return carteBancaireService.addBankCard(accountID,carteBancaire);
    }
    @PostMapping("/{cardId}/activate")
    public CarteBancaire activateCard(@PathVariable Long cardId) throws Exception {
        return carteBancaireService.activateCard(cardId);
    }

    @PostMapping("/{cardId}/deactivate")
    public CarteBancaire deactivateCard(@PathVariable Long cardId) throws Exception {
        return carteBancaireService.deactivateCard(cardId);
    }
    @PostMapping("/{cardId}/block")
    public void blockCard(@PathVariable Long cardId, @RequestParam String reason) throws Exception {
        carteBancaireService.blockCard(cardId, reason);
    }
}
