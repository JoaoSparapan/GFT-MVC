package com.gft.campeonato.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.campeonato.entities.Academia;
import com.gft.campeonato.repositories.AcademiaRepository;

@Service
public class AcademiaService {

	@Autowired
	private AcademiaRepository ar;
	
	public Academia salvarAcademia(Academia a)
	{
		return ar.save(a);
	}
	
	public List<Academia> listarAcademias()
	{
		return ar.findAll();
	}
	
	public Academia obterAcademia(Long id) throws Exception
	{
		Optional<Academia> a = ar.findById(id);
		if(a.isEmpty()) {
			throw new Exception("Academia não encontrada.");		
		}
		
		return a.get();
	}

	public void excluirAcademia(Long id) {
		ar.deleteById(id);
	}
}
