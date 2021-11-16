package com.gft.campeonato.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.campeonato.entities.Campeonato;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long>{

	List<Campeonato> findByNomeContainsAndSiglaContains(String nome, String sigla);
}
