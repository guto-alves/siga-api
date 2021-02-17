package com.gutotech.sigaapi.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Falta {
	@EmbeddedId
	private FaltaId id = new FaltaId();

	private int totalFaltas;

	public Falta() {
	}

	public Falta(Aluno aluno, Disciplina disciplina, Date data, int totalFaltas) {
		id.setAluno(aluno);
		id.setDisciplina(disciplina);
		id.setData(data);
		this.totalFaltas = totalFaltas;
	}

	public int getTotalFaltas() {
		return totalFaltas;
	}

	public void setTotalFaltas(int totalFaltas) {
		this.totalFaltas = totalFaltas;
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
		if (!(obj instanceof Falta)) {
			return false;
		}
		Falta other = (Falta) obj;
		return Objects.equals(id, other.id);
	}
}
