package com.gutotech.sigaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.sigaapi.model.Aluno;
import com.gutotech.sigaapi.repository.AlunoRepository;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repository;

	public List<Aluno> findAll() {
		return repository.findAllByOrderByNome();
	}

	public Aluno findByRa(String ra) {
		return repository.findById(ra).orElseThrow(() -> new IllegalArgumentException("Aluno not found."));
	}

	public void save(Aluno aluno) {
		repository.save(aluno);
	}

	public List<Aluno> findAllByDisciplina(long disciplinaId) {
		return repository.findAllByDisciplinaByOrderByNome(disciplinaId);
	}

}
