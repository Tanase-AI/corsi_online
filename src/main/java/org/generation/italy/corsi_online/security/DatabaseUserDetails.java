package org.generation.italy.corsi_online.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.generation.italy.corsi_online.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails {
    private static final long serialVersionUID = -6795977782141894959L;

    private final User utente;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(User utente) {
        super();
        this.utente = utente;
        authorities = new HashSet<GrantedAuthority>();
        for (Ruolo r : utente.getRuoli())
            authorities.add(new SimpleGrantedAuthority(r.getName()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        return utente.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUtente() {
        return utente;
    }

}
