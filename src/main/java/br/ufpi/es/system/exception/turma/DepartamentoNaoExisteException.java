package br.ufpi.es.system.exception.turma;

@SuppressWarnings("serial")
public class DepartamentoNaoExisteException extends Exception{
	public DepartamentoNaoExisteException() {
		super("Esse departamento n�o existe.");
	}
}
