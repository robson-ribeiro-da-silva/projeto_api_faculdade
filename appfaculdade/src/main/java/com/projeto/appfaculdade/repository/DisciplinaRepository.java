package com.projeto.appfaculdade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.appfaculdade.model.Disciplina;
import com.projeto.appfaculdade.model.Professor;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	List<Disciplina> findByProfessor(Professor professor);

}
