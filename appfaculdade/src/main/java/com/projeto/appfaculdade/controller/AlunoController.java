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

import com.projeto.appfaculdade.model.Aluno;
import com.projeto.appfaculdade.repository.AlunoRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	/**
	 * Método que adiciona uma novo Aluno
	 * @param Parâmentro do tipo Aluno
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Adiciona um novo aluno")
	public Aluno addFaculdade(@Valid @RequestBody Aluno aluno){
		
		return alunoRepository.save(aluno);
	}
	
	/**
	 * Método que retorna uma lista de todos Alunos
	 * @return
	 */
	@GetMapping
	@Operation(summary="Lista todos alunos")
	public  ResponseEntity<List<Aluno>> findAll(){
		
		return ResponseEntity.ok(alunoRepository.findAll());
	}	
	
	/**
	 * Método que atualiza um Aluno existente apartir do identificador
	 * @param aluno - Parâmentro do tipo Aluno
	 * @param id - um Long referente ao identificador da Aluno
	 * @return
	 */
	@PutMapping("/{id}")
	@Operation(summary="Atualiza um aluno existente")
	public ResponseEntity<Aluno> updateAluno(@Valid @PathVariable("id") Long id, @RequestBody Aluno aluno){
		
		if(!alunoRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		aluno.setId(id);		
		return ResponseEntity.ok(alunoRepository.save(aluno));
	}
	
	/**
	 * Método que remove um Aluno existente apartir do identificador
	 * @param id - um Long referente ao identificador do Aluno 
	 * @return
	 */
	@DeleteMapping("/{id}")
	@Operation(summary="Remove um aluno")
	public ResponseEntity<Void> deleteAluno(@PathVariable("id") Long id){
		
		if(!alunoRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		
		alunoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
