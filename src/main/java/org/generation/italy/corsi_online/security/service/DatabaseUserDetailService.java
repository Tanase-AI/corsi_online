package org.generation.italy.corsi_online.security.service;

import java.util.Optional;

import org.generation.italy.corsi_online.model.User;
import org.generation.italy.corsi_online.repository.UserRepository;
import org.generation.italy.corsi_online.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = utenteRepository.findByUsername(username);
        if (user.isPresent())
            return new DatabaseUserDetails(user.get());
        else
            throw new UsernameNotFoundException("Utente non trovato");
    }

}
