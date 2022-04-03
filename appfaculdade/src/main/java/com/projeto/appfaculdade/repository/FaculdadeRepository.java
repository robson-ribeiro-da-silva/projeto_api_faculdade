package com.projeto.appfaculdade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.appfaculdade.model.Faculdade;

@Repository
public interface FaculdadeRepository extends JpaRepository<Faculdade, Long> {

}
