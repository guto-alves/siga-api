package com.gutotech.sigaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gutotech.sigaapi.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

	@Query("SELECT n FROM Nota n WHERE n.id.avaliacao.id = :avaliacao ORDER BY n.id.aluno.nome")
	List<Nota> findAllByAvaliacao(long avaliacao);
	
	@Query("SELECT n FROM Nota n ORDER BY n.id.aluno.nome")
	List<Nota> findAllByOrderByAluno();
}
