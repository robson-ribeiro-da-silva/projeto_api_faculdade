package com.projeto.appfaculdade.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.appfaculdade.model.Disciplina;
import com.projeto.appfaculdade.repository.AlunoRepository;
import com.projeto.appfaculdade.repository.DisciplinaRepository;
import com.projeto.appfaculdade.repository.ProfessorRepository;
import com.projeto.appfaculdade.service.DisciplinaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	/**
	 * Método que adiciona uma nova Disciplina
	 * @param Parâmentro do tipo Disciplina
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Adiciona uma nova disciplina")
	public Disciplina addDisciplina(@Valid @RequestBody Disciplina disciplina){
		
		disciplina.setCriacao(LocalDateTime.now());
		return disciplinaRepository.save(disciplina);
	}
	
	/**
	 * Método que retorna uma lista de todas Disciplinas
	 * @return
	 */
	@GetMapping
	@Operation(summary="Lista todas disciplinas")
	public  ResponseEntity<List<Disciplina>> findAll(){
		
		return ResponseEntity.ok(disciplinaRepository.findAll());
	}	
	
	/**
	 * Método que atualiza uma Disciplina existente apartir do identificador
	 * @param disciplina - Parâmentro do tipo Disciplina
	 * @param id - um Long referente ao identificador do Disciplina
	 * @return
	 */
	@PutMapping("/{id}")
	@Operation(summary="Atualiza uma disciplina existente")
	public ResponseEntity<Disciplina> updateDisciplina(@Valid @PathVariable("id") Long id, @RequestBody Disciplina disciplina){
		
		if(!disciplinaRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}		
		
		Disciplina disciplinaaux = disciplinaRepository.findById(id).get();
		
		disciplina.setId(id);	
		disciplina.setAlteracao(LocalDateTime.now());
		disciplina.setCriacao(disciplinaaux.getCriacao());
		return ResponseEntity.ok(disciplinaRepository.save(disciplina));
	}
	
	/**
	 * Método que aloca um Professor a uma Disciplina 
	 * @param idDisciplina - Parâmentro id referente a Disciplina
	 * @param idProfessor - Parâmentro id referente ao Professor
	 * @return
	 */
	@PutMapping("/professor/{idDiciplina}/{idProfessor}")
	@Operation(summary="Aloca um professor a uma disciplina")
	public ResponseEntity<Disciplina> alocarProfessor(@PathVariable("idDiciplina") Long idDiciplina, @PathVariable("idProfessor") Long idProfessor){
		
		if(!disciplinaRepository.existsById(idDiciplina)){
			return ResponseEntity.notFound().build();
		}
		
		if(!professorRepository.existsById(idProfessor)){
			return ResponseEntity.notFound().build();
		}	
		return ResponseEntity.ok(disciplinaService.alocarProfessor(idDiciplina, idProfessor));
	}
	
	/**
	 * Método que aloca um Aluno a uma Disciplina 
	 * @param idDisciplina - Parâmentro id referente a Disciplina
	 * @param idAluno - Parâmentro id referente ao Aluno
	 * @return
	 */
	@PutMapping("/aluno/{idDiciplina}/{idAluno}")
	@Operation(summary="Aloca um aluno a uma disciplina")
	public ResponseEntity<Disciplina> alocarAluno(@PathVariable("idDiciplina") Long idDiciplina, @PathVariable("idAluno") Long idAluno){
		
		if(!disciplinaRepository.existsById(idDiciplina)){
			return ResponseEntity.notFound().build();
		}
		
		if(!alunoRepository.existsById(idAluno)){
			return ResponseEntity.notFound().build();
		}	
		return ResponseEntity.ok(disciplinaService.alocarAluno(idDiciplina, idAluno));
	}

}
