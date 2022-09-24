package br.inatel.labs.labrest.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class WebClientDeleteCursoPeloId {
	public static void main (String[] args) {	
		try {
			ResponseEntity<Void> responseEntity = WebClient.create("http://localhost:8080")
					.delete()
					.uri("/curso/3")
					.retrieve()
					.toBodilessEntity()
					.block();
			
			HttpStatus statusCode = responseEntity.getStatusCode();
			
			System.out.println("Curso removido. Status da resposta " + statusCode);
		} catch (WebClientResponseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getStatusCode());
			System.out.println(e.getMessage());
		}
				
	}
}
