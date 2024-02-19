package org.generation.italy.corsi_online.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

@Entity
public class DateEsami {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_corso")
    private Corso corso;

    private LocalDateTime dataEsame;

    public DateEsami() {
        super();
    }

    // Costruttore con parametri
    public DateEsami(Corso corso, LocalDateTime dataEsame) {
        this.corso = corso;
        this.dataEsame = dataEsame;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public LocalDateTime getDataEsame() {
        return dataEsame;
    }

    public void setDataEsame(LocalDateTime dataEsame) {
        this.dataEsame = dataEsame;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "DateEsami{" +
                "id=" + id +
                ", corso=" + corso +
                ", dataEsame=" + dataEsame +
                '}';
    }

}
