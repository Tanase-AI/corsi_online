package org.generation.italy.corsi_online.repository;

import org.generation.italy.corsi_online.model.DateEsami;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateEsamiRepository extends JpaRepository<DateEsami, Integer> {

}
