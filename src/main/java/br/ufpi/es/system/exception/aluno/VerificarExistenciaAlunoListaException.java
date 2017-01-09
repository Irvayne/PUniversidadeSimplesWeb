package br.ufpi.es.system.exception.aluno;

public class VerificarExistenciaAlunoListaException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2329439532459582370L;

	public VerificarExistenciaAlunoListaException(){
		super("Verificar existencia aluno: Aluno já Existe!");
	}
}
