package com.banksolution.ebank.service;

import com.banksolution.ebank.model.CarteBancaire;
import com.banksolution.ebank.repository.CarteBancaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteBancaireService {

    @Autowired
    private CarteBancaireRepository carteBancaireRepository;

    public List<CarteBancaire> findAll() {
        return carteBancaireRepository.findAll();
    }

}
