package com.gutotech.sigaapi.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String tipo;

	private double peso;

	public Avaliacao() {
	}
	
	public Avaliacao(Long id) {
		this.id = id;
	}

	public Avaliacao(String tipo) {
		this.tipo = tipo;
	}

	public Avaliacao(String tipo, double peso) {
		this.tipo = tipo;
		this.peso = peso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Avaliacao)) {
			return false;
		}
		Avaliacao other = (Avaliacao) obj;
		return Objects.equals(id, other.id);
	}
}
