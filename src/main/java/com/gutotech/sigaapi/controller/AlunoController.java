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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gutotech.sigaapi.model.Aluno;
import com.gutotech.sigaapi.service.AlunoService;

@RestController
@RequestMapping("alunos")
public class AlunoController {
	@Autowired
	private AlunoService service;

	@GetMapping
	public ResponseEntity<List<Aluno>> findAll(@RequestParam(name = "disciplina", required = false) Long id) {
		return ResponseEntity.ok(id == null ? service.findAll() : service.findAllByDisciplina(id));
	}

	@GetMapping("/{ra}")
	public ResponseEntity<Aluno> findByRa(@PathVariable("ra") String ra) {
		return ResponseEntity.ok(service.findByRa(ra));
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Aluno aluno) {
		service.save(aluno);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(aluno.getRa())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
