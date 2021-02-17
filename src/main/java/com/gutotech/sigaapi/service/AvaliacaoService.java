package com.gutotech.sigaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.sigaapi.model.Avaliacao;
import com.gutotech.sigaapi.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository repository;

	public List<Avaliacao> findAll() {
		return repository.findAll();
	}

	public void save(Avaliacao avaliacao) {
		repository.save(avaliacao);
	}

}
