package br.inatel.labs.labrest.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.inatel.labs.labrest.server.model.Curso;

/**
 * Service com método de negócio para curso
 * @author Antonio]
 *
 */
@Service
public class CursoService {
	
	private List<Curso> listaDeCursos = new ArrayList<>();
	
	@PostConstruct
	private void setup() {
		Curso c1 = new Curso(1L, "Rest com spring boot", 20);
		Curso c2 = new Curso(2L, "Programação Java 11", 80);
		Curso c3 = new Curso(3L, "Dominando a IDE Eclipse", 120);
		
		listaDeCursos.add(c1);
		listaDeCursos.add(c2);
		listaDeCursos.add(c3);
	}
	
	public List<Curso> pesquisarCurso() {		
		return listaDeCursos;
	}
	
	public Optional<Curso> buscarCursoPeloId(long cursoId) {		
		Optional<Curso> opCurso = listaDeCursos.stream()
			.filter(c -> c.getId().equals(cursoId))
			.findFirst();
		return opCurso;
	}
	
	public List<Curso> buscarCursoPeloFragmentoDescricao(String fragDescricao) {		
		if(Objects.isNull(fragDescricao) || fragDescricao.isBlank()) {
			return List.of();
		}
		
		List<Curso> listaDeCursosEncontrados = this.listaDeCursos.stream()
				.filter(c -> c.getDescricao().trim().toLowerCase().contains(fragDescricao.trim().toLowerCase()))
				.collect(Collectors.toList());
		
		return listaDeCursosEncontrados;
	}
	
	public Curso criarCurso(Curso curso) {		
		Long id = new Random().nextLong();
		curso.setId(id);
		listaDeCursos.add(curso);
		return curso;
	}
	
	public Curso atualizarCurso(Curso curso) {		
		listaDeCursos.remove(curso);
		listaDeCursos.add(curso);
		return curso;
	}
	
	public void removerCursoPeloId(Long cursoId) {		
		Optional<Curso> opCurso = buscarCursoPeloId(cursoId);
		if(opCurso.isPresent()) {
			listaDeCursos.remove(opCurso.get());
		} else {
			String msg = String.format("Nenhum curso encontrado para ser removido com id [%s]", cursoId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
		}
	}
}
