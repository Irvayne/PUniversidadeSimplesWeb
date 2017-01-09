package br.ufpi.es.system.exception.aluno;

public class AlterarAlunoListaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 772145173265430406L;

	public AlterarAlunoListaException() {
		super("Alteração de aluno falhou!");
	}

}
