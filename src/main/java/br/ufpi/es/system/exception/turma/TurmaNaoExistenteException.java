package br.ufpi.es.system.exception.turma;

@SuppressWarnings("serial")
public class TurmaNaoExistenteException extends Exception{
	
	public TurmaNaoExistenteException(){
		super("Essa turma nao existe!");
	}
}
