package com.gutotech.sigaapi.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class NotaId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Aluno aluno;

	@ManyToOne
	private Disciplina disciplina;

	@ManyToOne
	private Avaliacao avaliacao;

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

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aluno, avaliacao, disciplina);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof NotaId)) {
			return false;
		}
		NotaId other = (NotaId) obj;
		return Objects.equals(aluno, other.aluno) && Objects.equals(avaliacao, other.avaliacao)
				&& Objects.equals(disciplina, other.disciplina);
	}
}
