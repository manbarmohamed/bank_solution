package com.banksolution.ebank.model;

import com.banksolution.ebank.model.enums.TypeCompte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeCompte type;

    private double solde;
    private LocalDate dateCreation;
    private boolean actif;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "compte")
    private List<CarteBancaire> cartesBancaires;

    @OneToMany(mappedBy = "compte")
    private List<Beneficiaire> beneficiaries;


}