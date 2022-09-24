package br.inatel.labs.labrest.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.inatel.labs.labrest.client.model.Curso;
import reactor.core.publisher.Flux;

public class WebClientGetCurso {
	public static void main (String[] args) {
		try {
			List<Curso> listaCurso = new ArrayList<Curso>();
			
			Flux<Curso> fluxCurso = WebClient.create("http://localhost:8080")
					.get()
					.uri("/curso")
					.retrieve()
					.bodyToFlux(Curso.class);
			
			fluxCurso.subscribe(c -> listaCurso.add(c));
			fluxCurso.blockLast();
			
			System.out.println("Lista de cursos: ");
			System.out.println(listaCurso);
		} catch (WebClientResponseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getStatusCode());
			System.out.println(e.getMessage());
		}				
	}
}
