package com.projeto.appfaculdade.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.appfaculdade.model.Aluno;
import com.projeto.appfaculdade.model.Disciplina;
import com.projeto.appfaculdade.model.Professor;
import com.projeto.appfaculdade.repository.AlunoRepository;
import com.projeto.appfaculdade.repository.DisciplinaRepository;
import com.projeto.appfaculdade.repository.ProfessorRepository;


@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public Disciplina alocarProfessor(Long idDisciplina, Long idProfessor) {
		
		Disciplina disciplina = disciplinaRepository.findById(idDisciplina).get();
		Professor professor = professorRepository.findById(idProfessor).get();
		
		disciplina.setProfessor(professor);
		
		return disciplinaRepository.save(disciplina);
	}
	
	public Disciplina alocarAluno(Long idDisciplina, Long idAluno) {
		
		Disciplina disciplina = disciplinaRepository.findById(idDisciplina).get();
		Aluno aluno = alunoRepository.findById(idAluno).get();
		
		if(aluno.getDisciplinas().contains(disciplina)) {
			return disciplina;
		}
		
		disciplina.getAlunos().add(aluno);
		
		return disciplinaRepository.save(disciplina);
	}
}
