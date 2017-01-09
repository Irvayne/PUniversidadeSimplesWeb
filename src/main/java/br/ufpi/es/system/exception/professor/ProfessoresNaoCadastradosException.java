package br.ufpi.es.system.exception.professor;

@SuppressWarnings("serial")
public class ProfessoresNaoCadastradosException extends Exception{
	public ProfessoresNaoCadastradosException() {
		super("Nenhum professor foi cadastrados no repositorio.");
	}
}
