package br.inatel.labs.labrest.server.controler;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest.server.model.Curso;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CursoControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;

	
	@Test
	void deveListarCursos() {
		webTestClient.get()
			.uri("/curso")
			.exchange()
			.expectStatus()
				.isOk() // status is ok ?
			.expectBody()
				.returnResult(); //retornou algum resultado ?
		
	}
	
	@Test
	void dadoCursoIdValido_quandoGetCursoPeloId_entaoRespondeComCursoValido() {
		Long cursoIdValido = 1L;
		
		Curso cursoRespondido = webTestClient.get()
			.uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus()
				.isOk()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		//assertNotNull(cursoRespondido);
		//assertEquals(cursoRespondido.getId(), cursoIdValido);
		
		assertThat(cursoRespondido).isNotNull();
		assertThat(cursoIdValido).isEqualTo(cursoRespondido.getId());
	}
	
	@Test
	void dadoCursoIdInvalido_quandoGetCursoPeloId_entaoRespondeComStatusNotFound() {
		Long cursoIdInvalido = 99L;
		
		webTestClient.get()
			.uri("/curso/" + cursoIdInvalido)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	void dadoNovoCurso_quandoPostCurso_entaoRespondeComStatusCreatedECursoValido() {
		Curso novoCurso = new Curso();
		novoCurso.setDescricao("Testando REST com Spring WebFlux");
		novoCurso.setCargaHoraria(160);
		
		Curso cursoRespondido = webTestClient.post()
			.uri("/curso")
			.bodyValue(novoCurso)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(Curso.class).returnResult().getResponseBody();
		
		assertThat(cursoRespondido).isNotNull();
		assertThat(cursoRespondido.getId()).isNotNull();
	}
	
	@Test
	void dadoCurso_quandoPutCurso_entaoRespondeComStatusAcceptedECorpoVazio() {
		Curso curso = new Curso();
		curso.setId(1L);
		curso.setDescricao("descricao atualizada");
		curso.setCargaHoraria(111);
		
		webTestClient.put()
			.uri("/curso")
			.bodyValue(curso)
			.exchange()
			.expectStatus()
				.isAccepted()
			.expectBody()
				.isEmpty();
	}
	
	@Test
	void dadoCursoIdValido_quandoDeleteCurso_entaoRespondeComStatusNoContent() {
		Long cursoIdValido = 1L;
		
		webTestClient.delete()
			.uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus().isNoContent();
	}


	@Test
	void dadoCursoIdInvalido_quandoDeleteCurso_entaoRespondeComStatusNotFound() {
		Long cursoIdValido = 133L;
		
		webTestClient.delete()
			.uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus().isNotFound();
	}
}
