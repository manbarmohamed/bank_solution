package com.banksolution.ebank.service;

import com.banksolution.ebank.exception.CarteBancaireNotFoundException;
import com.banksolution.ebank.model.CarteBancaire;
import com.banksolution.ebank.model.Compte;
import com.banksolution.ebank.repository.CarteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service pour gérer les opérations sur les cartes bancaires.
 */
@Service
public class CarteBancaireService {

    @Autowired
    private CarteBancaireRepository carteBancaireRepository;

    @Autowired
    private CompteService compteService;

    /**
     * Récupère la liste de toutes les cartes bancaires.
     *
     * @return une liste de toutes les cartes bancaires.
     */
    public List<CarteBancaire> findAll() {
        return carteBancaireRepository.findAll();
    }

    /**
     * Ajoute une nouvelle carte bancaire à un compte.
     *
     * @param accountId l'identifiant du compte.
     * @param bankCard la carte bancaire à ajouter.
     * @return la carte bancaire ajoutée.
     */
    public CarteBancaire addBankCard(Long accountId, CarteBancaire bankCard) {
        Compte account = compteService.getCompteById(accountId);

        boolean cardExists = account.getCartesBancaires().stream()
                .anyMatch(card -> card.getType() == bankCard.getType());

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

    /**
     * Active une carte bancaire.
     *
     * @param cardId l'identifiant de la carte bancaire.
     * @return la carte bancaire activée.
     * @throws Exception si la carte bancaire n'est pas trouvée.
     */
    public CarteBancaire activateCard(Long cardId)  {
        Optional<CarteBancaire> cardOpt = carteBancaireRepository.findById(cardId);
        if (cardOpt.isPresent()) {
            CarteBancaire card = cardOpt.get();
            card.setActive(true);
            return carteBancaireRepository.save(card);
        } else {
            throw new CarteBancaireNotFoundException("Card not found.");
        }
    }

    /**
     * Désactive une carte bancaire.
     *
     * @param cardId l'identifiant de la carte bancaire.
     * @return la carte bancaire désactivée.
     * @throws Exception si la carte bancaire n'est pas trouvée.
     */
    public CarteBancaire deactivateCard(Long cardId) throws Exception {
        Optional<CarteBancaire> cardOpt = carteBancaireRepository.findById(cardId);
        if (cardOpt.isPresent()) {
            CarteBancaire card = cardOpt.get();
            card.setActive(false);
            return carteBancaireRepository.save(card);
        } else {
            throw new CarteBancaireNotFoundException("Card not found.");
        }
    }

    /**
     * Bloque une carte bancaire.
     *
     * @param cardId l'identifiant de la carte bancaire.
     * @param reason la raison du blocage.
     * @throws Exception si la carte bancaire n'est pas trouvée.
     */
    public void blockCard(Long cardId, String reason) throws Exception {
        Optional<CarteBancaire> cardOpt = carteBancaireRepository.findById(cardId);
        if (cardOpt.isPresent()) {
            CarteBancaire card = cardOpt.get();
            card.setActive(false);
            card.setBloquee(true);
            card.setRaisonBlocage(reason);
            carteBancaireRepository.save(card);
        } else {
            throw new CarteBancaireNotFoundException("Card not found.");
        }
    }

    /**
     * Récupère la liste des cartes bancaires associées à un compte.
     *
     * @param compte l'identifiant du compte.
     * @return une liste de cartes bancaires associées au compte.
     */
    public List<CarteBancaire> findByCompteId(Long compte) {
        return carteBancaireRepository.findByCompteId(compte);
    }
}
