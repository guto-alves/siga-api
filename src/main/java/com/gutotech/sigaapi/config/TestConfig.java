package com.gutotech.sigaapi.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gutotech.sigaapi.model.Aluno;
import com.gutotech.sigaapi.model.Avaliacao;
import com.gutotech.sigaapi.model.Disciplina;
import com.gutotech.sigaapi.model.Falta;
import com.gutotech.sigaapi.repository.DisciplinaRepository;
import com.gutotech.sigaapi.repository.FaltaRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private FaltaRepository faltaRepository;

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void run(String... args) throws Exception {
		Aluno aluno1 = new Aluno("1110481822020", "Gustavo Alves");
		Aluno aluno2 = new Aluno("1110481822021", "Caique Vidal");
		Aluno aluno3 = new Aluno("1110481822022", "Raizer Varela");
		Aluno aluno4 = new Aluno("1110481822023", "Victor Neves");
		Aluno aluno5 = new Aluno("1110481822024", "Murillo Silva");
		Aluno aluno6 = new Aluno("1110481822025", "Maria João");
		Aluno aluno7 = new Aluno("1110481822026", "Beatriz Silva");
		Aluno aluno8 = new Aluno("1110481822027", "Albert Einstein");
		Aluno aluno9 = new Aluno("1110481822028", "Nicollò Paganni");

		Disciplina disciplina2 = new Disciplina("Arquitetura e Organização de Computadores", "IAC001", "Tarde", 80);
		Disciplina disciplina1 = new Disciplina("Laboratório de Hardware", "IHW100", "Tarde", 40);
		Disciplina disciplina3 = new Disciplina("Banco de Dados", "IBD002", "Tarde", 80);
		Disciplina disciplina5 = new Disciplina("Sistemas Operacionais II", "ISO200", "Tarde", 80);
		Disciplina disciplina4 = new Disciplina("Laboratório de Banco de Dados", "IDB100", "Tarde", 80);
		Disciplina disciplina6 = new Disciplina("Métodos Para a Produção do Conhecimento", "TTG100", "Tarde", 80);

		disciplina1.getAlunos().addAll(List.of(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8, aluno9));
		disciplina2.getAlunos().addAll(List.of(aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8));
		disciplina3.getAlunos().addAll(List.of(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8, aluno9));
		disciplina4.getAlunos().addAll(List.of(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8));
		disciplina5.getAlunos().addAll(List.of(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8));
		disciplina6.getAlunos().addAll(List.of(aluno1, aluno2, aluno3, aluno9));
		disciplinaRepository
				.saveAll(List.of(disciplina1, disciplina2, disciplina3, disciplina4, disciplina5, disciplina6));

		// Avalicao
		Avaliacao av1 = new Avaliacao("P1", 0.3);
		Avaliacao av2 = new Avaliacao("P2", 0.5);
		Avaliacao av3 = new Avaliacao("T", 0.2);
		Avaliacao av4 = new Avaliacao("Exame");

		Avaliacao av5 = new Avaliacao("P1", 0.35);
		Avaliacao av6 = new Avaliacao("P2", 0.35);
		Avaliacao av7 = new Avaliacao("T", 0.3);
		Avaliacao av8 = new Avaliacao("Pré Exame", 0.2);
		Avaliacao av9 = new Avaliacao("Exame");

		Avaliacao av10 = new Avaliacao("P1", 0.333);
		Avaliacao av11 = new Avaliacao("P2", 0.333);
		Avaliacao av12 = new Avaliacao("P3", 0.333);
		Avaliacao av13 = new Avaliacao("Exame");

		Avaliacao av14 = new Avaliacao("Monografia Resumida", 0.8);
		Avaliacao av15 = new Avaliacao("Monografia Completa", 0.2);
		Avaliacao av16 = new Avaliacao("Exame", 0.2);

		disciplina1.getAvaliacaos().addAll(List.of(av1, av2, av3, av4));
		disciplina2.getAvaliacaos().addAll(List.of(av1, av2, av3, av4));
		disciplina3.getAvaliacaos().addAll(List.of(av1, av2, av3, av4));
		disciplina5.getAvaliacaos().addAll(List.of(av5, av6, av7, av8, av9));
		disciplina4.getAvaliacaos().addAll(List.of(av10, av11, av12, av13));
		disciplina6.getAvaliacaos().addAll(List.of(av14, av15, av16));
		disciplinaRepository
				.saveAll(List.of(disciplina1, disciplina2, disciplina3, disciplina4, disciplina5, disciplina6));

		// Chamada
		// 01/11/2020
		Falta falta1 = new Falta(aluno1, disciplina4, dateFormat.parse("01/11/2020"), 1);
		Falta falta2 = new Falta(aluno2, disciplina4, dateFormat.parse("01/11/2020"), 0);
		Falta falta3 = new Falta(aluno3, disciplina4, dateFormat.parse("01/11/2020"), 4);
		Falta falta4 = new Falta(aluno4, disciplina4, dateFormat.parse("01/11/2020"), 0);
		Falta falta5 = new Falta(aluno5, disciplina4, dateFormat.parse("01/11/2020"), 0);
		Falta falta6 = new Falta(aluno6, disciplina4, dateFormat.parse("01/11/2020"), 4);
		Falta falta7 = new Falta(aluno7, disciplina4, dateFormat.parse("01/11/2020"), 0);
		Falta falta8 = new Falta(aluno8, disciplina4, dateFormat.parse("01/11/2020"), 4);

		// 08/11/2020
		Falta falta9 = new Falta(aluno1, disciplina4, dateFormat.parse("08/11/2020"), 1);
		Falta falta10 = new Falta(aluno2, disciplina4, dateFormat.parse("08/11/2020"), 4);
		Falta falta11 = new Falta(aluno3, disciplina4, dateFormat.parse("08/11/2020"), 0);
		Falta falta12 = new Falta(aluno4, disciplina4, dateFormat.parse("08/11/2020"), 4);
		Falta falta13 = new Falta(aluno5, disciplina4, dateFormat.parse("08/11/2020"), 0);
		Falta falta14 = new Falta(aluno6, disciplina4, dateFormat.parse("08/11/2020"), 0);
		Falta falta15 = new Falta(aluno7, disciplina4, dateFormat.parse("08/11/2020"), 0);
		Falta falta16 = new Falta(aluno8, disciplina4, dateFormat.parse("08/11/2020"), 0);

		// 16/11/2020
		Falta falta17 = new Falta(aluno1, disciplina4, dateFormat.parse("16/11/2020"), 1);
		Falta falta18 = new Falta(aluno2, disciplina4, dateFormat.parse("16/11/2020"), 4);
		Falta falta19 = new Falta(aluno3, disciplina4, dateFormat.parse("16/11/2020"), 0);
		Falta falta20 = new Falta(aluno4, disciplina4, dateFormat.parse("16/11/2020"), 0);
		Falta falta21 = new Falta(aluno5, disciplina4, dateFormat.parse("16/11/2020"), 2);
		Falta falta22 = new Falta(aluno6, disciplina4, dateFormat.parse("16/11/2020"), 0);
		Falta falta23 = new Falta(aluno7, disciplina4, dateFormat.parse("16/11/2020"), 0);
		Falta falta24 = new Falta(aluno8, disciplina4, dateFormat.parse("16/11/2020"), 0);

		faltaRepository.saveAll(List.of(falta1, falta2, falta3, falta4, falta5, falta6, falta7, falta8, falta9, falta10,
				falta11, falta12, falta13, falta14, falta15, falta16, falta17, falta18, falta19, falta20, falta21,
				falta22, falta23, falta24));
	}

}
