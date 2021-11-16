package com.gft.campeonato.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.campeonato.entities.Esporte;
import com.gft.campeonato.repositories.EsporteRepository;


@Service
public class EsporteService {

	@Autowired
	private EsporteRepository er;
	
	public Esporte salvarEsporte(Esporte e)
	{
		return er.save(e);
	}
	
	public List<Esporte> listarEsportes()
	{
		return er.findAll();
	}
	
	public Esporte obterEsporte(Long id) throws Exception
	{
		Optional<Esporte> e = er.findById(id);
		if(e.isEmpty()) {
			throw new Exception("Esporte não encontrado.");		
		}
		
		return e.get();
	}

	public void excluirEsporte(Long id) {
		er.deleteById(id);
	}
}
