package br.ufpi.es.universidadesimples.entidades;

public class Usuario {

	private String email;
	private String senha;
	private String nome;	

	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

}
