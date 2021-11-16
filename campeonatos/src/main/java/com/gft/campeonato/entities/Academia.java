package com.gft.campeonato.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Academia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Este campo não pode ficar em branco!")
	private String nome;
		
	@NotEmpty(message = "Este campo não pode ficar em branco!")
	@Email
	private String email;
	
	@Digits(fraction = 2, integer = 10)
	private BigDecimal mensalidade;
	
	@ManyToOne
	private Esporte esporte;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(BigDecimal mensalidade) {
		this.mensalidade = mensalidade;
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}
	
	
}
