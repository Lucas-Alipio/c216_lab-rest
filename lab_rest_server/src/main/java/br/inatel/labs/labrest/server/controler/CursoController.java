package br.inatel.labs.labrest.server.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.labs.labrest.server.service.CursoService;
import br.inatel.labs.labrest.server.exception.CursoNaoEncontradoException;
import br.inatel.labs.labrest.server.model.Curso;

@RestController //gerencia ciclo de vida 
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService service;
	
	@GetMapping
	public List<Curso> listar() {
		List<Curso> listaCurso = service.pesquisarCurso();
		
		return listaCurso;
	}
	
	@GetMapping("/{id}")
	public Curso buscar(@PathVariable("id") Long cursoId) {
		Optional<Curso> opCurso = service.buscarCursoPeloId(cursoId);
		
		if (opCurso.isPresent()) {
			return opCurso.get();
		} else {
			throw new CursoNaoEncontradoException(cursoId);
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Curso criar(@RequestBody Curso curso) {
		Curso cursoCriado = service.criarCurso(curso);
		
		return cursoCriado;
	}
	
	@PutMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void atualizar (@RequestBody Curso curso) {
		service.atualizarCurso(curso);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("id") Long cursoIdParaRemover) {
		Optional<Curso> opCurso = service.buscarCursoPeloId(cursoIdParaRemover);
		
		if (opCurso.isEmpty()) {
			throw new CursoNaoEncontradoException(cursoIdParaRemover);
		} else {
			Curso cursoASerRemovido = opCurso.get();
			service.removerCurso(cursoASerRemovido);
		}
	}
	
	@GetMapping("/pesquisa")
	public List<Curso> listarPeloFragDescricao(@RequestParam("descricao") String fragDescricao) {
		return service.pesquisarCursoPeloFragDescricao(fragDescricao);
	}
	
	
	//Get And Set
	public CursoService getService() {
		return service;
	}

	public void setService(CursoService service) {
		this.service = service;
	}
}
