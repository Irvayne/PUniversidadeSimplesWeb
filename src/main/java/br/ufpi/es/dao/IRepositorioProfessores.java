package br.ufpi.es.dao;

import java.util.List;

import br.ufpi.es.model.Professor;
import br.ufpi.es.model.Turma;
import br.ufpi.es.system.exception.professor.ProfessorNaoExistenteException;
import br.ufpi.es.system.exception.professor.ProfessoresNaoCadastradosException;;

public interface IRepositorioProfessores {

	/**
	 * Insere um professor.
	 * 
	 * @param professor
	 *            .
	 */
	public void insereProfessor(Professor professor)throws Exception;

	/**
	 * Dado um cpf, retorna o professor correspondente.
	 * 
	 * @param cpf
	 *            .
	 * @return professor.
	 */
	public Professor buscarProfessor(String cpf)
			throws ProfessorNaoExistenteException, Exception;

	/**
	 * Checa se existe professor dado o cpf.
	 * 
	 * @param cpf
	 *            .
	 * @return true se existe, false se nao existe.
	 */
	public boolean verificaExistenciaProfessor(String cpf) throws Exception;;

	/**
	 * Metodo que altera os dados de um determinado professor. A opcao do
	 * atributo a ser alterado, o cpf do professor e a nova informacao devem ser
	 * informados. As opcoes sao: 1 - CPF 2 - Nome 3 - Titulo 4 - Lotacao
	 * 
	 * @param op
	 *            , cp, info.
	 */
	public void alterarProfessor(Professor prof , String cpfInformadoPeloUsuario)
			throws ProfessorNaoExistenteException, Exception;

	/**
	 * Dada o cpf remove o professor.
	 * 
	 * @param cpf
	 *            .
	 */
	public void removerProfessor(String cpf)
			throws ProfessorNaoExistenteException, Exception;

	/**
	 * Lista todos os professores do repositorio de professores.
	 * 
	 * @return Lista de professores
	 */
	public List<Professor> listarProfessores()
			throws ProfessoresNaoCadastradosException,Exception;

	/**
	 * Quantidade de professores do repositorio
	 * 
	 * @return quantidade.
	 */
	public int quantidadeProfessor() throws Exception;;

	public List<Turma> listarTurmas(Professor professor);
}
