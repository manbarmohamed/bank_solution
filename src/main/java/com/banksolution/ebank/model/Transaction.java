package com.banksolution.ebank.model;

import com.banksolution.ebank.model.enums.TypeTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateHeure;
    private double montant;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    private String description;

    @ManyToOne
    @JoinColumn(name = "compte_id")

    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "beneficiare_id")
    private Beneficiaire beneficiaire;
}