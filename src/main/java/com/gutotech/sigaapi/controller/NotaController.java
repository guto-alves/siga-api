package com.gutotech.sigaapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gutotech.sigaapi.model.Aluno;
import com.gutotech.sigaapi.model.Avaliacao;
import com.gutotech.sigaapi.model.Disciplina;
import com.gutotech.sigaapi.model.Nota;
import com.gutotech.sigaapi.model.Prova;
import com.gutotech.sigaapi.service.NotaService;

@Controller
@RequestMapping("notas")
public class NotaController {
	@Autowired
	private NotaService service;

	@GetMapping
	public ResponseEntity<List<Nota>> findAllBy(
			@RequestParam(name = "avaliacao", required = false) Long avaliacaoId) {
		if (avaliacaoId == null) {
			return ResponseEntity.ok(service.findAll());
		}

		return ResponseEntity.ok(service.findAllByAvaliacao(new Avaliacao(avaliacaoId)));
	}
	
	@Autowired
	private EntityManager entityManager;

	@GetMapping("quadro/{disciplina}")
	public ResponseEntity<String> getQuadroNotas(@PathVariable("disciplina") long disciplinaId) {
		entityManager
				.createStoredProcedureQuery("sp_build_quadro_notas")
				.registerStoredProcedureParameter("disciplina_id", Long.class, ParameterMode.IN)
				.setParameter("disciplina_id", disciplinaId)
				.execute();
		
		Query query = entityManager.createNativeQuery("SELECT * FROM quadro_notas FOR JSON PATH");
		
		return ResponseEntity.ok((String) query.getSingleResult());
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Prova prova) {
		long disciplinaId = prova.getDisciplina();
		Long avaliacaoId = prova.getAvaliacao();

		List<Nota> notas = prova.getAlunoNota().stream().map(alunoNota -> {
			String ra = alunoNota.get("ra");
			double nota = Double.parseDouble(alunoNota.get("nota"));
			
			return new Nota(new Aluno(ra), new Avaliacao(avaliacaoId), new Disciplina(disciplinaId), nota);
		}).collect(Collectors.toList());

		service.saveAll(notas);

		return ResponseEntity.ok().build();
	}
}
