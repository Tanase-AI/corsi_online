package org.generation.italy.corsi_online.repository;

import java.util.List;

import org.generation.italy.corsi_online.model.PPA;
import org.generation.italy.corsi_online.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PPARepository extends JpaRepository<PPA, Integer> {
    List<PPA> findByStudente(User studente);
}
