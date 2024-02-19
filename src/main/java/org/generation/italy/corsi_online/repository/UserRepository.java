package org.generation.italy.corsi_online.repository;

import java.util.Optional;

import org.generation.italy.corsi_online.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}
