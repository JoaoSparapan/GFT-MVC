package com.gft.campeonato.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.campeonato.entities.Campeonato;
import com.gft.campeonato.repositories.CampeonatoRepository;

@Service
public class CampeonatoService {

	@Autowired
	private CampeonatoRepository cr;
	
	public Campeonato salvarCampeonato(Campeonato camp)
	{
		return cr.save(camp);
	}
	
	public List<Campeonato> listarCampeonatos(String nome, String sigla)
	{
		if(nome!=null || sigla!=null)
			return listarCampeonatosPorNomeeSigla(nome, sigla);
		
		return listarTodosCampeonatos();
	
	}
	
	public List<Campeonato> listarTodosCampeonatos()
	{
		return cr.findAll();
	}
	
	private List<Campeonato> listarCampeonatosPorNomeeSigla(String nome, String quatroLetras)
	{
		return cr.findByNomeContainsAndSiglaContains(nome, quatroLetras);
	}
	
	public Campeonato obterCampeonato(Long id) throws Exception
	{
		Optional<Campeonato> camp = cr.findById(id);
		
		if(camp.isEmpty()) {
			throw new Exception("Campeonato não encontrado.");		
		}
		
		return camp.get();
	}
	
	public void excluirCampeonato(Long id)
	{
		cr.deleteById(id);
	}
}
