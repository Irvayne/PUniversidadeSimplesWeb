package br.ufpi.es.universidadesimples.system.exception.aluno;

@SuppressWarnings("serial")
public class AlunoNaoExistenteException extends Exception{
	
	public AlunoNaoExistenteException(){
		super("N�o exite aluno cadastrado com essas informacoes");
	}
}
