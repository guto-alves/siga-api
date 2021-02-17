package com.gutotech.sigaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gutotech.sigaapi.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String> {

	@Query("SELECT a FROM Disciplina d JOIN d.alunos a WHERE d.id = :disciplinaId ORDER BY a.nome")
	List<Aluno> findAllByDisciplinaByOrderByNome(Long disciplinaId);
	
	List<Aluno> findAllByOrderByNome();
}
