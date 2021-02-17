package com.gutotech.sigaapi.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Nota {
	@EmbeddedId
	private NotaId id = new NotaId();

	private double nota;

	public Nota() {
	}

	public Nota(Aluno aluno, Avaliacao avaliacao, Disciplina disciplina, double nota) {
		id.setAluno(aluno);
		id.setAvaliacao(avaliacao);
		id.setDisciplina(disciplina);
		this.nota = nota;
	}

	public Aluno getAluno() {
		return id.getAluno();
	}

	public void setAluno(Aluno aluno) {
		id.setAluno(aluno);
	}

	@JsonIgnore
	public Avaliacao getAvaliacao() {
		return id.getAvaliacao();
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		id.setAvaliacao(avaliacao);
	}

	@JsonIgnore
	public Disciplina getDisciplina() {
		return id.getDisciplina();
	}

	public void setDisciplina(Disciplina disciplina) {
		id.setDisciplina(disciplina);
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
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
		if (!(obj instanceof Nota)) {
			return false;
		}
		Nota other = (Nota) obj;
		return Objects.equals(id, other.id);
	}
}
