package br.ufpi.es.universidadesimples.model;

import java.util.LinkedList;
import java.util.List;

public class Aluno {
	
	private String matricula;
	private String nome;
	private String curso;
	private List<Turma> turmas;
	
	/*
	 * Cria uma instancia passando os atributos diretamente na criacao do objeto
	 */
	public Aluno(String matricula, String nome, String curso){
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
		this.turmas = new LinkedList<Turma>();
	}

	/*
	 * Metodos de acesso aos atributos do objeto
	 */
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	
	
}