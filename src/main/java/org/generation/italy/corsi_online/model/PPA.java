package org.generation.italy.corsi_online.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

// P.renotazione con P.agamento A.nticipato 
@Entity
public class PPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_studente")
    private User studente;

    private LocalDateTime dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "dataEsamePrenotato")
    private DateEsami dataEsame;

    @Column(precision = 6, scale = 2)
    private BigDecimal importo;

    @PrePersist
    public void PrePersist() {
        dataPrenotazione = LocalDateTime.now();
    }

    public void Studente(User user) {
        // TODO Auto-generated method stub
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Corso getCorso() {
        return dataEsame.getCorso();
    }

    // public void setCorso(Corso corso) {
    // this.dataEsame.getCorso() = corso;
    // }

    public User getStudente() {
        return studente;
    }

    public void setStudente(User studente) {
        this.studente = studente;
    }

    public LocalDateTime getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDateTime dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public DateEsami getDataEsame() {
        return dataEsame;
    }

    public void setDataEsame(DateEsami dataEsame) {
        this.dataEsame = dataEsame;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

}
