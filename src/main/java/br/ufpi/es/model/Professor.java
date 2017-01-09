package br.ufpi.es.model;

import java.util.LinkedList;
import java.util.List;

public class Professor {
	
	private String cpf;
	private String nome;
	private String lotacao;
	private String titulo;
	private List<Turma> turmas;
	
	/*
	 * Cria uma instancia passando os atributos diretamente na criacao do objeto
	 */
	public Professor(String cpf, String nome, String lotacao, String titulo) {
		this.cpf = cpf;
		this.nome = nome;
		this.lotacao = lotacao;
		this.titulo = titulo;
		this.turmas = new LinkedList<Turma>();
	}

	/*
	 * Metodos de acesso aos atributos do objeto
	 */
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	

}
