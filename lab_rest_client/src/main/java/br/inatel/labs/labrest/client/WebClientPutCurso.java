package br.inatel.labs.labrest.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.inatel.labs.labrest.client.model.Curso;

public class WebClientPutCurso {
	public static void main (String[] args) {	
		try {
			
			Curso cursoExistente = new Curso();
			cursoExistente.setDescricao("Rest com Sprint Boot e Spring WebFlux");
			cursoExistente.setCargaHoraria(80);
			cursoExistente.setId(1L);
			
			ResponseEntity<Void> responseEntity = WebClient.create("http://localhost:8080")
					.put()
					.uri("/curso")
					.bodyValue(cursoExistente)
					.retrieve()
					.toBodilessEntity()
					.block();
			
			HttpStatus statusCode = responseEntity.getStatusCode();
			
			System.out.println("Curso atualizado. Status da resposta " + statusCode);
		} catch (WebClientResponseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getStatusCode());
			System.out.println(e.getMessage());
		}
				
	}
}
