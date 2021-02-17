package com.gutotech.sigaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.sigaapi.model.Avaliacao;
import com.gutotech.sigaapi.model.Nota;
import com.gutotech.sigaapi.repository.NotaRepository;

@Service
public class NotaService {

	@Autowired
	private NotaRepository repository;

	public List<Nota> findAll() {
		return repository.findAllByOrderByAluno();
	}

	public List<Nota> findAllByAvaliacao(Avaliacao avaliacao) {
		return repository.findAllByAvaliacao(avaliacao.getId());
	}

	public void save(Nota nota) {
		repository.save(nota);
	}

	public void saveAll(List<Nota> notas) {
		repository.saveAll(notas);
	}
}
