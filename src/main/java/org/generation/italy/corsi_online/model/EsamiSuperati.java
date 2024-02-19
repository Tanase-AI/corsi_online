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
}