package br.ufpi.es.system.exception.aluno;

@SuppressWarnings("serial")
public class AlunosNaoCadastradosException extends Exception{
	
	public AlunosNaoCadastradosException(){
		super("Nenhum aluno foi cadastrado no repositorio.");
	}
}
