package com.projeto.appfaculdade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.appfaculdade.model.Disciplina;
import com.projeto.appfaculdade.model.Professor;
import com.projeto.appfaculdade.repository.DisciplinaRepository;
import com.projeto.appfaculdade.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	public void removeDisciplina(Long id) {
		
		Professor professor = professorRepository.findById(id).get();
		List<Disciplina> disciplinas = disciplinaRepository.findByProfessor(professor);
		
		if(!disciplinas.isEmpty()) {
			for(Disciplina disciplina : disciplinas) {
				disciplina.setProfessor(null);
				disciplinaRepository.save(disciplina);
			}
		}
	}

}
