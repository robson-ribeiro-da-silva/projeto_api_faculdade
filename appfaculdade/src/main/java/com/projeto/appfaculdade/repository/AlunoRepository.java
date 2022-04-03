package com.projeto.appfaculdade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.appfaculdade.model.Aluno;
import com.projeto.appfaculdade.model.Curso;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	List<Aluno> findByCurso(Curso curso);

}
