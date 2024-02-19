package org.generation.italy.corsi_online.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ruolo {
    // campi obbligatori per la security
    @Id
    private Integer id;

    @NotNull
    private String name; // nome del ruolo (es: admin, user, ...)

    public Ruolo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
