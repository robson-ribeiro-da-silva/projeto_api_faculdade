package com.projeto.appfaculdade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.appfaculdade.model.Aluno;
import com.projeto.appfaculdade.model.Curso;
import com.projeto.appfaculdade.model.Disciplina;
import com.projeto.appfaculdade.repository.AlunoRepository;
import com.projeto.appfaculdade.repository.CursoRepository;
import com.projeto.appfaculdade.repository.DisciplinaRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;

	public void removeAluno(Long id) {
		
		Curso curso = cursoRepository.findById(id).get();
		List<Aluno> alunos  = alunoRepository.findByCurso(curso);
		
		if(!alunos.isEmpty()) {
			for(Aluno aluno : alunos) {
				aluno.setCurso(null);
				alunoRepository.save(aluno);
			}
		}
	}
	
	public Curso alocarDisciplina(Long idCurso, Long idDisciplina) {
		
		Curso curso = cursoRepository.findById(idCurso).get();
		Disciplina disciplina = disciplinaRepository.findById(idDisciplina).get();
		
		if(disciplina.getCursos().contains(curso)) {
			return curso;
		}
		curso.getDisciplinas().add(disciplina);
		
		return cursoRepository.save(curso);
	}

}
