package org.generation.italy.corsi_online.model;

import jakarta.persistence.*;

@Entity
public class EsamiSuperati {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_studente")
    private User studente;

    @ManyToOne
    @JoinColumn(name = "id_corso")
    private Corso corso;

    // Costruttore vuoto
    public EsamiSuperati() {
    }

    // Costruttore con parametri
    public EsamiSuperati(User studente, Corso corso) {
        this.studente = studente;
        this.corso = corso;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudente() {
        return studente;
    }

    public void setStudente(User studente) {
        this.studente = studente;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "EsamiSuperati{" +
                "id=" + id +
                ", studente=" + studente +
                ", corso=" + corso +
                '}';
    }

}