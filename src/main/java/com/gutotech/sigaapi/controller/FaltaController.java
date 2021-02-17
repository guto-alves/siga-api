package com.gutotech.sigaapi.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutotech.sigaapi.model.Aluno;
import com.gutotech.sigaapi.model.Chamada;
import com.gutotech.sigaapi.model.Disciplina;
import com.gutotech.sigaapi.model.Falta;
import com.gutotech.sigaapi.service.FaltaService;

@RestController
@RequestMapping("faltas")
public class FaltaController {
	@Autowired
	private FaltaService service;

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@GetMapping
	public ResponseEntity<List<Falta>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@Autowired
	private EntityManager entityManager;

	@GetMapping("quadro/{disciplina}")
	public ResponseEntity<String> getQuadroFaltas(@PathVariable("disciplina") long disciplinaId) {
		entityManager
				.createStoredProcedureQuery("sp_build_quadro_faltas")
				.registerStoredProcedureParameter("disciplina_id", Long.class, ParameterMode.IN)
				.setParameter("disciplina_id", disciplinaId)
				.execute();
		
		Query query = entityManager.createNativeQuery("SELECT * FROM quadro_faltas FOR JSON PATH");
		
		return ResponseEntity.ok((String) query.getSingleResult());
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Chamada chamada) {
		long disciplinaId = chamada.getDisciplinaId();

		Date data;
		try {
			data = dateFormat.parse(chamada.getData());
		} catch (ParseException e) {
			data = new Date();
			e.printStackTrace();
		}

		Date d = data;

		List<Falta> faltas = chamada.getFaltasAlunos().stream().map(alunosFaltas -> {
			String ra = alunosFaltas.get("alunoRa");
			int totalFaltas = Integer.parseInt(alunosFaltas.get("totalFaltas"));

			return new Falta(new Aluno(ra), new Disciplina(disciplinaId), d, totalFaltas);
		}).collect(Collectors.toList());

		service.saveAll(faltas);

		return ResponseEntity.ok().build();
	}
}
