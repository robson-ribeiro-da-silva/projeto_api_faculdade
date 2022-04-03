package com.projeto.appfaculdade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.appfaculdade.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
