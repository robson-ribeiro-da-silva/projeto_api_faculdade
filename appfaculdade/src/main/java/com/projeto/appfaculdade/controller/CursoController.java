package com.projeto.appfaculdade.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.appfaculdade.model.Curso;
import com.projeto.appfaculdade.repository.CursoRepository;
import com.projeto.appfaculdade.repository.DisciplinaRepository;
import com.projeto.appfaculdade.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/api/cursos")
public class CursoController {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	/**
	 * Método que adiciona uma novo Curso
	 * @param Parâmentro do tipo Curso
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Adiciona um novo curso")
	public Curso addCurso(@Valid @RequestBody Curso curso){
		
		curso.setCriacao(LocalDateTime.now());
		return cursoRepository.save(curso);
	}
	
	/**
	 * Método que retorna uma lista de todos Cursos
	 * @return
	 */
	@GetMapping
	@Operation(summary="Lista todos cursos")
	public  ResponseEntity<List<Curso>> findAll(){
		
		return ResponseEntity.ok(cursoRepository.findAll());
	}	
	

	/**
	 * Método que atualiza um Curso existente apartir do identificador
	 * @param curso - Parâmentro do tipo Curso
	 * @param id - um Long referente ao identificador do Curso
	 * @return
	 */
	@PutMapping("/{id}")
	@Operation(summary="Atualiza um curso existente")
	public ResponseEntity<Curso> updateCurso(@Valid @PathVariable("id") Long id, @RequestBody Curso curso){
		
		if(!cursoRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		Curso cursoaux = cursoRepository.findById(id).get();
		
		curso.setId(id);	
		curso.setAlteracao(LocalDateTime.now());
		curso.setCriacao(cursoaux.getCriacao());
		return ResponseEntity.ok(cursoRepository.save(curso));
	}
	
	/**
	 * Método que remove um Curso existente apartir do identificador
	 * @param id - um Long referente ao identificador do Curso
	 * @return
	 */
	@DeleteMapping("/{id}")
	@Operation(summary="Remove um curso existente")
	public ResponseEntity<Void> deleteCurso(@PathVariable("id") Long id){
		
		if(!cursoRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		
		cursoService.removeAluno(id);
		cursoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	/**
	 * Método que aloca uma Disciplina a um Curso
	 * @param idDisciplina - Parâmentro id referente a Disciplina
	 * @param idCurso - Parâmentro id referente ao Curso
	 * @return
	 */
	@PutMapping("/{idCurso}/{idDisciplina}")
	@Operation(summary="Aloca uma disciplina a um curso")
	public ResponseEntity<Curso> alocarDisciplina(@PathVariable("idCurso") Long idCurso, @PathVariable("idDisciplina") Long idDisciplina){
		
		if(!disciplinaRepository.existsById(idDisciplina)){
			return ResponseEntity.notFound().build();
		}
		
		if(!cursoRepository.existsById(idCurso)){
			return ResponseEntity.notFound().build();
		}	
		return ResponseEntity.ok(cursoService.alocarDisciplina(idCurso, idDisciplina));
	}

}
