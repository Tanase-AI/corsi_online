package org.generation.italy.corsi_online.repository;

import java.util.List;
import java.util.Optional;

import org.generation.italy.corsi_online.model.Corso;
import org.generation.italy.corsi_online.model.PPA;
import org.generation.italy.corsi_online.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PPARepository extends JpaRepository<PPA, Long> {
    List<PPA> findByStudente(User studente);
    Optional<Corso> findById(long id);
}
