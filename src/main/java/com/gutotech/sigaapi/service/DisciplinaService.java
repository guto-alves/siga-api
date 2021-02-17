package com.gutotech.sigaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.sigaapi.model.Disciplina;
import com.gutotech.sigaapi.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
	@Autowired
	private DisciplinaRepository repository;

	public List<Disciplina> findAll() {
		return repository.findAll();
	}

	public Disciplina findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Disciplina not found."));
	}

	public void save(Disciplina disciplina) {
		repository.save(disciplina);
	}

}
