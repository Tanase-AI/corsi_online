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

// P.renotazione con P.agamento A.nticipato 
@Entity
public class PPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne // ! da togliere
    @JoinColumn(name = "id_corso")
    private Corso corso;

    @ManyToOne
    @JoinColumn(name = "id_studente")
    private User studente;

    private LocalDateTime dataPrenotazione;

    @ManyToOne
    @JoinColumn(name = "dataEsamePrenotato")
    private DateEsami dataEsame;

    @Column(precision = 6, scale = 2)
    private BigDecimal importo;

}
