package br.ufpi.es.universidadesimples.system.exception.aluno;

public class RemoverAlunoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 296076834742797478L;

	public RemoverAlunoException() {
		super("Remover aluno da lista: FALHA!");
	}

}
