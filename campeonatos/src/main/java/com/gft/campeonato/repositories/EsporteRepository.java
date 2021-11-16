package com.gft.campeonato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.campeonato.entities.Esporte;

@Repository
public interface EsporteRepository extends JpaRepository<Esporte, Long>{

}
