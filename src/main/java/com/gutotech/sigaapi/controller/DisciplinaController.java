package com.gutotech.sigaapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gutotech.sigaapi.model.Avaliacao;
import com.gutotech.sigaapi.model.Disciplina;
import com.gutotech.sigaapi.service.DisciplinaService;

@RestController
@RequestMapping("disciplinas")
public class DisciplinaController {
	@Autowired
	private DisciplinaService service;

	@GetMapping
	public ResponseEntity<List<Disciplina>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping("/{id}/avaliacoes")
	public ResponseEntity<List<Avaliacao>> findAllAvaliacoesByDisciplina(@PathVariable("id") long id) {
		return ResponseEntity.ok(service.findById(id).getAvaliacaos());
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Disciplina disciplina) {
		service.save(disciplina);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(disciplina.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
