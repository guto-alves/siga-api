package com.gutotech.sigaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.sigaapi.model.Falta;

@Repository
public interface FaltaRepository extends JpaRepository<Falta, Long> {

//	@Query("SELECT f FROM Falta f WHERE f.id.aluno :aluno")
//	List<Falta> findByAluno(Aluno aluno);
}
