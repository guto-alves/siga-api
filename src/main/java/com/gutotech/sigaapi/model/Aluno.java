package com.gutotech.sigaapi.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluno {
	@Id
	private String ra;

	private String nome;

	public Aluno() {
	}

	public Aluno(String ra) {
		this.ra = ra;
	}
	
	public Aluno(String ra, String nome) {
		this.ra = ra;
		this.nome = nome;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, ra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Aluno)) {
			return false;
		}
		Aluno other = (Aluno) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(ra, other.ra);
	}

}
