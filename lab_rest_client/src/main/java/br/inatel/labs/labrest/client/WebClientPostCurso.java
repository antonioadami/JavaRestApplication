package br.inatel.labs.labrest.client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.inatel.labs.labrest.client.model.Curso;

public class WebClientPostCurso {
	public static void main (String[] args) {	
		try {
			
			Curso novoCurso = new Curso();
			novoCurso.setDescricao("Dominando Spring WebFlux");
			novoCurso.setCargaHoraria(80);
			
			Curso cursoCriado = WebClient.create("http://localhost:8080")
					.post()
					.uri("/curso")
					.bodyValue(novoCurso)
					.retrieve()
					.bodyToMono(Curso.class)
					.block();
			
			System.out.println("Curso criado: ");
			System.out.println(cursoCriado);
		} catch (WebClientResponseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getStatusCode());
			System.out.println(e.getMessage());
		}
				
	}
}
