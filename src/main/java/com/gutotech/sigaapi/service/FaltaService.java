package com.gutotech.sigaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.sigaapi.model.Falta;
import com.gutotech.sigaapi.repository.FaltaRepository;

@Service
public class FaltaService {

	@Autowired
	private FaltaRepository repository;

	public void save(Falta falta) {
		repository.save(falta);
	}

	public List<Falta> findAll() {
		return repository.findAll();
	}

	public void saveAll(List<Falta> faltas) {
		repository.saveAll(faltas);
	}

//	public List<Falta> findByAluno(Aluno aluno) {
//		return repository.findByAluno(aluno);
//	}
}
