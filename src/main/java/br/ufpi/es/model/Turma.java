package br.ufpi.es.model;

import java.util.LinkedList;
import java.util.List;

public class Turma {
	private int idTurma;
	private String departamento;
	private String disciplina;
	private int cargaHoraria;
	private int quantidadeAlunos;
	private Professor professor;
	private List<Aluno> alunos;
	
	/*
	 * Cria uma instancia passando alguns dos atributos diretamente na criacao do objeto.
	 */
	public Turma(String departamento, String disciplina, int horario) {
		this.departamento = departamento;
		this.disciplina = disciplina;
		this.cargaHoraria = horario;
		this.alunos = new LinkedList<Aluno>();
	}
	
	/*
	 * Metodos de acesso aos atributos do objeto.
	 */
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}

	/**
	 * Insere uma lista de alunos
	 * @param aluno
	 */
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}	
	/**
	 * Insere um dado professor na turma
	 * @param aluno
	 */
	public void insereProfessor(Professor professor){
		this.professor = professor;
	}
	
	
	/**
	 * Insere um dado aluno na lista de alunos
	 * @param aluno
	 */
	public void insereAluno(Aluno aluno){
		alunos.add(aluno);
	}
	
	/**
	 * Remove um dado aluno na lista de alunos
	 * @param aluno
	 */
	public void removeAluno(Aluno aluno){
		alunos.remove(aluno);
	}
	
	/**
	 * @return the quantidadeAlunos
	 */
	public int getQuantidadeAlunos() {
		return quantidadeAlunos;
	}

	/**
	 * @param quantidadeAlunos the quantidadeAlunos to set
	 */
	public void setQuantidadeAlunos(int quantidadeAlunos) {
		this.quantidadeAlunos = quantidadeAlunos;
	}
	
	public int getIdTurma() {
		return idTurma;
	}

	/**
	 * Identificador unico da turma
	 * @param idTurma
	 */
	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
}