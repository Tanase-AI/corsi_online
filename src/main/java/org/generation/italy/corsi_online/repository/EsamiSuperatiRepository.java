package org.generation.italy.corsi_online.repository;

import java.util.List;

import org.generation.italy.corsi_online.model.EsamiSuperati;
import org.generation.italy.corsi_online.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsamiSuperatiRepository extends JpaRepository<EsamiSuperati, Long> {
    List<EsamiSuperati> findByStudente(User studente);

}
