package org.generation.italy.corsi_online.repository;

import org.generation.italy.corsi_online.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Short> {
    // Puoi aggiungere metodi personalizzati se necessario
}
