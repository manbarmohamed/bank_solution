package com.banksolution.ebank.service;

import com.banksolution.ebank.model.CarteBancaire;
import com.banksolution.ebank.model.Compte;
import com.banksolution.ebank.repository.CarteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarteBancaireService {

    @Autowired
    private CarteBancaireRepository carteBancaireRepository;
    @Autowired
    private CompteService compteService;

    public List<CarteBancaire> findAll() {
        return carteBancaireRepository.findAll();
    }
    public CarteBancaire addBankCard(Long accountId, CarteBancaire bankCard) {
        Compte account = compteService.getCompteById(accountId);

        boolean cardExists = account.getCartesBancaires().stream().anyMatch(card -> card.getType() == bankCard.getType());

        if (cardExists) {
            throw new IllegalArgumentException("A card of the same type already exists for this account.");
        }
        bankCard.setNumero(UUID.randomUUID().toString());
        bankCard.setDateExpiration(LocalDate.now().plusYears(4));
        bankCard.setActive(true);
        bankCard.setBloquee(false);
        bankCard.setCompte(account);

        carteBancaireRepository.save(bankCard);

        return bankCard;
    }
    public CarteBancaire activateCard(Long cardId) throws Exception {
        Optional<CarteBancaire> cardOpt = carteBancaireRepository.findById(cardId);
        if (cardOpt.isPresent()) {
            CarteBancaire card = cardOpt.get();
            card.setActive(true);
            return carteBancaireRepository.save(card);
        } else {
            throw new Exception("Card not found.");
        }
    }
    public CarteBancaire deactivateCard(Long cardId) throws Exception {
        Optional<CarteBancaire> cardOpt = carteBancaireRepository.findById(cardId);
        if (cardOpt.isPresent()) {
            CarteBancaire card = cardOpt.get();
            card.setActive(false);
            return carteBancaireRepository.save(card);
        } else {
            throw new Exception("Card not found.");
        }
    }

    public void blockCard(Long cardId, String reason) throws Exception {
        Optional<CarteBancaire> cardOpt = carteBancaireRepository.findById(cardId);
        if (cardOpt.isPresent()) {
            CarteBancaire card = cardOpt.get();
            card.setActive(false);
            card.setBloquee(true);
            card.setRaisonBlocage(reason);
            carteBancaireRepository.save(card);
        } else {
            throw new Exception("Card not found.");
        }
    }

    public List<CarteBancaire> findByCompteId(Long compte) {
        return carteBancaireRepository.findByCompteId(compte);
    }

}
