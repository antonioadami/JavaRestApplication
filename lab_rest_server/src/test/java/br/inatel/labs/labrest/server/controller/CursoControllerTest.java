package br.inatel.labs.labrest.server.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest.server.model.Curso;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CursoControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void deveListarCursos() {
		webTestClient.get()
			.uri("/curso")
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.returnResult();
	}
	
	@Test
	void dadoCursoIdValido_quandoGetCursoPeloId_entaoRespondeComCursoValido() {
		Long cursoIdValido = 1L;
		
		Curso cursoRespondido = webTestClient.get()
			.uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertNotNull(cursoRespondido);
		assertEquals(cursoRespondido.getId(), cursoIdValido);
	}
	 
	@Test
	void dadoCursoIdInvalido_quandoGetCursoPeloId_entaoRespondeComStatusNotFound() {
		Long cursoIdValido = 99L;
		
		webTestClient.get()
			.uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	void dadoNovoCurso_quandoPostCurso_entaoRespondeComStatusCreatedCursoValido() {
		Curso novoCurso = new Curso();
		novoCurso.setCargaHoraria(120);
		novoCurso.setDescricao("Testetando REST com Spring WebFlux");
		
		Curso cursoRespondido = webTestClient.post()
			.uri("/curso")
			.bodyValue(novoCurso)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertNotNull(cursoRespondido);
		assertNotNull(cursoRespondido.getId());
	}
	
	@Test
	void dadoCursoAtualizado_quandoPutCurso_entaoRespondeComStatusAcceptedCursoValido() {
		Curso cursoAtualizado = new Curso();
		cursoAtualizado.setCargaHoraria(120);
		cursoAtualizado.setDescricao("Testetando REST PUT com Spring WebFlux");
		cursoAtualizado.setId(1L);
		
		Curso cursoRespondido = webTestClient.put()
			.uri("/curso")
			.bodyValue(cursoAtualizado)
			.exchange()
			.expectStatus().isAccepted()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertNotNull(cursoRespondido);
		assertNotNull(cursoRespondido.getId());
	}
	
	@Test
	void dadoCursoIdValido_quandoDeleteCurso_entaoRespondeComStatusNoContent() {		
		webTestClient.delete()
			.uri("/curso/3")
			.exchange()
			.expectStatus().isNoContent();
	}

}
