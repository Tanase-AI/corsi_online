package org.generation.italy.corsi_online.model;

import java.util.List;
import java.util.Set;

import org.generation.italy.corsi_online.security.Ruolo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 32)
    private String username;

    @Column(nullable = false, length = 32)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Ruolo> ruoli;

    @Column(nullable = false, length = 32)
    private String email;

    @Column(nullable = false, columnDefinition = "char(16)")
    private String carta;

    @OneToMany(mappedBy = "studente")
    private List<EsamiSuperati> esamiSuperati;

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Ruolo> getRuoli() {
        return ruoli;
    }

    public void setRuoli(Set<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarta() {
        return carta;
    }

    public void setCarta(String carta) {
        this.carta = carta;
    }

    public List<EsamiSuperati> getEsamiSuperati() {
        return esamiSuperati;
    }

    public void setEsamiSuperati(List<EsamiSuperati> esamiSuperati) {
        this.esamiSuperati = esamiSuperati;
    }

}
