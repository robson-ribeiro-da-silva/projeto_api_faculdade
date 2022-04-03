package com.projeto.appfaculdade.controller;

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

import com.projeto.appfaculdade.model.Professor;
import com.projeto.appfaculdade.repository.ProfessorRepository;
import com.projeto.appfaculdade.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private ProfessorService professorService;
	
	
	
	/**
	 * Método que adiciona uma novo Professor
	 * @param Parâmentro do tipo Professor
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Adiciona um novo professor")
	public Professor addProfessor(@Valid @RequestBody Professor professor){
		
		return professorRepository.save(professor);
	}
	
	/**
	 * Método que retorna uma lista de todos Professores
	 * @return
	 */
	@GetMapping
	@Operation(summary="Lista todos professores")
	public  ResponseEntity<List<Professor>> findAll(){
		
		return ResponseEntity.ok(professorRepository.findAll());
	}	
	
	/**
	 * Método que atualiza um Professor existente apartir do identificador
	 * @param professor - Parâmentro do tipo Professor
	 * @param id - um Long referente ao identificador da Professor
	 * @return
	 */
	@PutMapping("/{id}")
	@Operation(summary="Atualiza um professor existente")
	public ResponseEntity<Professor> updateProfessor(@Valid @PathVariable("id") Long id, @RequestBody Professor professor){
		
		if(!professorRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		professor.setId(id);		
		return ResponseEntity.ok(professorRepository.save(professor));
	}
	
	/**
	 * Método que remove um Professor existente apartir do identificador
	 * @param id - um Long referente ao identificador da Professor 
	 * @return
	 */
	@DeleteMapping("/{id}")
	@Operation(summary="Remove um professor")
	public ResponseEntity<Void> deleteProfessor(@PathVariable("id") Long id){
		
		if(!professorRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		
		professorService.removeDisciplina(id);
		professorRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
