package com.gutotech.sigaapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class FaltaId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Aluno aluno;

	@ManyToOne
	private Disciplina disciplina;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date data;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aluno, data, disciplina);
	}

	// TODO fix comparing faltas
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FaltaId)) {
			return false;
		}
		FaltaId other = (FaltaId) obj;
		return Objects.equals(aluno, other.aluno) && Objects.equals(data, other.data)
				&& Objects.equals(disciplina, other.disciplina);
	}
}
