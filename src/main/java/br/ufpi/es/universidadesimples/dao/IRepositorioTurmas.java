package br.ufpi.es.universidadesimples.dao;

import java.util.List;

import br.ufpi.es.universidadesimples.model.Aluno;
import br.ufpi.es.universidadesimples.model.Professor;
import br.ufpi.es.universidadesimples.model.Turma;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.turma.TurmaNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.turma.TurmasNaoCadastradasException;

public interface IRepositorioTurmas {

	/**
	 * @throws Exception 
	 * Insere uma turma.
	 * @param turma
	 * @throws
	 */
	public void insereTurma(Turma turma) throws Exception;

	/**
	 * Dada a descricao da disciplina, retorna um turma.
	 * 
	 * @param descricao
	 * @return turma.
	 * @throws Exception 
	 * @throws TurmaNaoExistenteException.
	 */
	public Turma buscarTurma(int idenficador)
			throws TurmaNaoExistenteException, Exception;

	/**
	 * Checa se existe turma dada a descricao.
	 * 
	 * @param descricao
	 * @return true se existe, false se nao existe.
	 * @throws Exception 
	 */
	public boolean verificaExistenciaTurma(int idenficador) throws Exception;

	/**
	 * Metodo que altera os dados de uma determinada turma. A opcao do atributo
	 * a ser alterado, a disciplina e a nova informacao devem ser informados. As
	 * opcoes sao: 1 - Departamento. 2 - Disciplina 3 - Horario 4 - Quantidade
	 * de alunos.
	 * 
	 * @param op
	 * @param identificador
	 * @param info
	 * @throws TurmaNaoExistenteException
	 * @throws Exception 
	 */
	public void alterarTurma(int op, int identificador, String info)
			throws TurmaNaoExistenteException, Exception;

	/**
	 * Dada a descricao da disciplina, remove a turma.
	 * 
	 * @param disciplina
	 * @throws TurmaNaoExistenteException.
	 */
	public void removerTurma(int identificador)
			throws TurmaNaoExistenteException, Exception;

	/**
	 * Lista todos as turmas do repositorio de turmas.
	 * 
	 * @return Lista de turmas.
	 * @throws Exception 
	 * @throws ProfessoresNaoCadastradosException.
	 */
	public List<Turma> listarTurmas() throws TurmasNaoCadastradasException, Exception;

	/**
	 * Quantidade de turmas no repositorio
	 * @return quantidade
	 * @throws Exception 
	 */
	public int quantidadeTurmas() throws Exception;
	
	/**
	 * Dada uma turma especifica, insere o aluno dado
	 * @param aluno
	 * @param turma
	 * @throws TurmasNaoCadastradasException
	 * @throws Exception 
	 */
	public void insereAlunoTurma(Aluno aluno, Turma turma) throws TurmasNaoCadastradasException, Exception;
	
	/**
	 * Dada uma turma especifica, remove o aluno dado
	 * @param aluno
	 * @param turma
	 * @throws AlunoNaoExistenteException
	 * @throws TurmasNaoCadastradasException
	 * @throws Exception 
	 */
	public void removeAlunoTurma(Aluno aluno, Turma turma) throws AlunoNaoExistenteException, TurmasNaoCadastradasException, Exception;	
	
	/**
	 * Dada uma turma, lista seus alunos
	 * @param turma
	 * @return lista de alunos
	 * @throws Exception 
	 */
	public List<Aluno> listaAlunos(Turma turma) throws Exception;
	
	/**
	 * Dada uma nova turma atualiza os dados da turma encontrada
	 * @param identificador da turma original
	 * @param novaTurma
	 * @throws Exception 
	 * @throws TurmaNaoExistenteException
	 */
	public void insereProfessorTurma(Professor  professor, Turma turma) throws TurmasNaoCadastradasException, Exception;
	
	public void alterarTurma(int identificador, Turma novaTurma) throws TurmaNaoExistenteException, Exception;
}