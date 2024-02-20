package org.generation.italy.corsi_online.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

import java.util.List;

@Entity
public class Corso implements Comparable<Corso> {

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

    // Costruttore vuoto
    public Corso() {
    }

    // Costruttore con parametri
    public Corso(String nome, String settore, BigDecimal prezzo) {
        this.nome = nome;
        this.settore = settore;
        this.prezzo = prezzo;
    }

    // Getters and Setters
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSettore() {
        return settore;
    }

    public void setSettore(String settore) {
        this.settore = settore;
    }

    public List<DateEsami> getDateEsami() {
        return dateEsami;
    }

    public void setDateEsami(List<DateEsami> dateEsami) {
        this.dateEsami = dateEsami;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public List<EsamiSuperati> getEsamiSuperati() {
        return esamiSuperati;
    }

    public void setEsamiSuperati(List<EsamiSuperati> esamiSuperati) {
        this.esamiSuperati = esamiSuperati;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", nome='" + nome + '/' +
                ", settore='" + settore + '/' +
                ", prezzo=" + prezzo +
                '}';
    }

    @Override
    public int compareTo(Corso c) {
        if (this.nome.compareTo(c.getNome()) != 0)
            return this.nome.compareTo(c.getNome());
        else {
            if (this.nome.compareTo(c.getNome()) != 0) 
                return this.nome.compareTo(c.getNome());
            else { 
                return this.prezzo.compareTo(c.prezzo);
            }
        }
    }

}
