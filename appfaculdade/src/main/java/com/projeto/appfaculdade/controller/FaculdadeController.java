package com.projeto.appfaculdade.controller;

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

import com.projeto.appfaculdade.model.Faculdade;
import com.projeto.appfaculdade.repository.FaculdadeRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/faculdade")
public class FaculdadeController {
	
	@Autowired
	private FaculdadeRepository faculdadeRepository;
	
	/**
	 * Método que adiciona uma nova Faculdade
	 * @param Parâmentro do tipo Faculdade
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary="Adiciona uma nova faculdade")
	public Faculdade addFaculdade(@Valid @RequestBody Faculdade faculdade){
		
		return faculdadeRepository.save(faculdade);
	}
	
	/**
	 * Método que retorna uma lista de todas Faculdades
	 * @return
	 */
	@GetMapping
	@Operation(summary="Lista todas faculdades")
	public  ResponseEntity<List<Faculdade>> findAll(){
		
		return ResponseEntity.ok(faculdadeRepository.findAll());
	}
	
	/**
	 * Método que atualiza uma Faculdade existente apartir do identificador
	 * @param faculdade - Parâmentro do tipo Faculdade
	 * @param id - um Long referente ao identificador da Faculdade
	 * @return
	 */
	@PutMapping("/{id}")
	@Operation(summary="Atualiza uma faculdade existente")
	public ResponseEntity<Faculdade> updateFaculdade(@Valid @PathVariable("id") Long id, @RequestBody Faculdade faculdade){
		
		if(!faculdadeRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		faculdade.setId(id);		
		return ResponseEntity.ok(faculdadeRepository.save(faculdade));
	}

}
