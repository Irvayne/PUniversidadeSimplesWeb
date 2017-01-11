package br.ufpi.es.universidadesimples.controller;

import java.util.List;

import br.ufpi.es.universidadesimples.model.Aluno;
import br.ufpi.es.universidadesimples.model.Professor;
import br.ufpi.es.universidadesimples.model.Turma;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunosNaoCadastradosException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessorNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessorSemTurmaException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessoresNaoCadastradosException;
import br.ufpi.es.universidadesimples.system.exception.professor.RemoverProfessorException;
import br.ufpi.es.universidadesimples.system.exception.turma.DepartamentoNaoExisteException;
import br.ufpi.es.universidadesimples.system.exception.turma.TurmaNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.turma.TurmaSemAlunoException;
import br.ufpi.es.universidadesimples.system.exception.turma.TurmasNaoCadastradasException;

public interface IFachada {
	
	/*###################  ALUNOS ######################*/
	public void inserirAluno(Aluno aluno) throws Exception; 
	public void alterarAluno(Aluno a) throws Exception;
	public void removerAluno(Aluno aluno) throws AlunoNaoExistenteException, Exception;
	public void removerAluno(String matricula) throws AlunoNaoExistenteException, Exception;
	public Aluno buscarAluno(String matricula) throws AlunoNaoExistenteException, Exception;
	public List<Aluno> listarAlunos() throws  AlunosNaoCadastradosException, Exception;
	public void matricularAlunoTurma(Aluno a, Turma t) throws TurmasNaoCadastradasException, Exception ;
	public void trancarTurmaAluno(Aluno a, Turma t) throws AlunoNaoExistenteException, TurmasNaoCadastradasException, Exception;
	public boolean verificaSeExisteAluno(String matricula) throws Exception;
	public int quantidadeAlunos() throws Exception;
	
	/*################### PROFESSORES ######################*/
	public void inserirProfessor(Professor professor) throws Exception;
	public void alterarProfessor(Professor prof , String cpfInformadoPeloUsuario) throws ProfessorNaoExistenteException, Exception;
	public void removerProfessor(String cpf) throws ProfessorNaoExistenteException, Exception;
	public Professor buscarProfessor(String cpf) throws ProfessorNaoExistenteException, Exception;
	public List<Professor> listarProfessores() throws  ProfessoresNaoCadastradosException, Exception;
	public List<Turma> listarTurmaPorProfessor(Professor professor) throws ProfessorSemTurmaException;
	public void associarProfessorTurma(Professor professor, Turma turma) throws Exception;
	public void removerProfessorTurma(Professor professor, Turma turma)throws RemoverProfessorException,Exception;
	public int quantidadeProfessores() throws Exception;
	public boolean verificaExistenciaProfessor(String cpf) throws Exception;
	
	/*################### TURMAS ######################*/
	public void inserirTurma(Turma turma) throws Exception;
	public void alterarTurma(int op, int identificador, String info) throws  TurmaNaoExistenteException, Exception;
	public void removerTurma(int identificador) throws TurmaNaoExistenteException, Exception;
	public Turma buscarTurma(int identificador) throws TurmaNaoExistenteException, Exception;
	public List<Turma> listarTurmas() throws TurmasNaoCadastradasException, Exception;
	public List<Aluno> listarAlunoPorTurma(Turma t) throws TurmaSemAlunoException, Exception;
	public int quantidadeTurma() throws Exception;
	public boolean verificaExistenciaTurma(int identificador) throws Exception;
	public List<Turma> listarTurmasPorDepartamento(String departamento) throws TurmasNaoCadastradasException, DepartamentoNaoExisteException, Exception;
	
}
