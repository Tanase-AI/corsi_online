package org.generation.italy.corsi_online.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

import java.util.List;

@Entity
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(nullable = false, length = 32)
    private String nome;

    @Column(nullable = false, length = 32) // ? maybe togliere
    private String settore;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corso")
    private List<DateEsami> dateEsami;

    @Column(precision = 6, scale = 2)
    private BigDecimal prezzo;

    @OneToMany(mappedBy = "corso")
    private List<EsamiSuperati> esamiSuperati;
    // Altri campi e metodi

}
