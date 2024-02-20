package org.generation.italy.corsi_online.repository;

import java.util.Optional;


import org.generation.italy.corsi_online.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CorsoRepository extends JpaRepository<Corso, Short> {
    // Puoi aggiungere metodi personalizzati se necessario
    // List<Corso> findById(Integer id);
    List<Corso> findByNome(String nome);
    List<Corso> findBySettore(String settore);
    Optional<Corso> findById(short id);
    List<Corso> findBySettoreLikeOrderByPrezzo (String settore);
    List<Corso> findByNomeIgnoreCase(String nome);
    List<Corso> findByNomeIgnoreCaseAndSettoreLike(String nome, String settore);
    List<Corso> findByNomeLike(String nome);
}



