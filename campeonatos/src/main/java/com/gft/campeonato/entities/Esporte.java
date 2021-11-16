package com.gft.campeonato.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;


@Entity
public class Esporte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Nome não pode ser vazio")
	private String descricao;
	
	@OneToMany(mappedBy = "esporte")
	private List<Academia> academias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Academia> getAcademias() {
		return academias;
	}

	public void setAcademias(List<Academia> academias) {
		this.academias = academias;
	}
	
	
}
