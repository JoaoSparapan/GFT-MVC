package com.gft.campeonato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.campeonato.entities.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Long>{

}
