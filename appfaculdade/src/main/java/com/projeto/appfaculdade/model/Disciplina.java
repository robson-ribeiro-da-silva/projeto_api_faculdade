package com.projeto.appfaculdade.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//@NotEmpty
	private int codigo;
	
	@NotBlank
	@Size(max = 50)
	private String descricao;

	@NotBlank
	@Size(max = 50)
	private String usuario;
		
	private int cargahorariatotal;
	
	private int cargahorariapratica;
	
	private int cargahorariateorica;
	
	private int quantidadealunos;
	
	//@NotNull
	private LocalDateTime criacao;
	
	private LocalDateTime alteracao;
	
	@ManyToMany
	private List<Aluno> alunos;
	
	@ManyToOne
	private Professor professor;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "disciplinas")
	private List<Curso> cursos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getCargahorariatotal() {
		return cargahorariatotal;
	}

	public void setCargahorariatotal(int cargahorariatotal) {
		this.cargahorariatotal = cargahorariatotal;
	}

	public int getCargahorariapratica() {
		return cargahorariapratica;
	}

	public void setCargahorariapratica(int cargahorariapratica) {
		this.cargahorariapratica = cargahorariapratica;
	}

	public int getCargahorariateorica() {
		return cargahorariateorica;
	}

	public void setCargahorariateorica(int cargahorariateorica) {
		this.cargahorariateorica = cargahorariateorica;
	}

	public int getQuantidadealunos() {
		return quantidadealunos;
	}

	public void setQuantidadealunos(int quantidadealunos) {
		this.quantidadealunos = quantidadealunos;
	}

	public LocalDateTime getCriacao() {
		return criacao;
	}

	public void setCriacao(LocalDateTime criacao) {
		this.criacao = criacao;
	}

	public LocalDateTime getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(LocalDateTime alteracao) {
		this.alteracao = alteracao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
