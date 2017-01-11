package br.ufpi.es.universidadesimples.system.exception.professor;

@SuppressWarnings("serial")
public class ProfessorSemTurmaException extends Exception{
	
	public ProfessorSemTurmaException(){
		super("Esse professor n�o leciona em nehuma turma!");
	}
}
