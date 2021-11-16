package com.gft.campeonato.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Campeonato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Este campo não pode ficar em branco!")
	private String nome;
	
	@NotEmpty(message = "Este campo não pode ficar em branco!")
	@Size(min = 4, max = 4, message = "Devem ser quatro letras")
	private String sigla;
	
	@ManyToOne
	private Esporte esporte;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataRealizacao;
	
	@Digits(fraction = 2, integer = 10)
	private BigDecimal premiacaoTotal;

	@ManyToMany
	private List<Academia> participantes;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public BigDecimal getPremiacaoTotal() {
		return premiacaoTotal;
	}

	public void setPremiacaoTotal(BigDecimal premiacaoTotal) {
		this.premiacaoTotal = premiacaoTotal;
	}

	public List<Academia> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Academia> participantes) {
		this.participantes = participantes;
	}
	
	
}
