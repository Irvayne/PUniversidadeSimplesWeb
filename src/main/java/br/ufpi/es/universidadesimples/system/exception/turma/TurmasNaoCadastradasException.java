package br.ufpi.es.universidadesimples.system.exception.turma;

@SuppressWarnings("serial")
public class TurmasNaoCadastradasException extends Exception{
	
	public TurmasNaoCadastradasException(){
		super("Nenhuma turma foi cadastrada ainda!");
	}
}
