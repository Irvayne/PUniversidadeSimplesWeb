package br.ufpi.es.universidadesimples.controller;

import java.util.LinkedList;
import java.util.List;

import br.ufpi.es.universidadesimples.dao.IRepositorioProfessores;
import br.ufpi.es.universidadesimples.model.Professor;
import br.ufpi.es.universidadesimples.model.Turma;
import br.ufpi.es.universidadesimples.system.exception.BuscaListaException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessorNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessorSemTurmaException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessoresNaoCadastradosException;
import br.ufpi.es.universidadesimples.system.exception.professor.RemoverProfessorException;

public class ProfessoresController {
	private IRepositorioProfessores controleProfessores;

	/**
	 * Insancia o controlador de acordo com o tipo de repositorio.
	 * 
	 * @param tipo
	 */
	public ProfessoresController(IRepositorioProfessores tipo) {
		this.controleProfessores = tipo;
	}

	/**
	 * Insere um professor no repositorio
	 * 
	 * @param professor
	 * @throws RepositorioException
	 */
	public void inserir(Professor professor) throws Exception{
		this.controleProfessores.insereProfessor(professor);
	}

	/**
	 * Dado o cpf retorna o professor.
	 * 
	 * @param cpf
	 *            .
	 * @return professor.
	 * @throws RepositorioException
	 * @throws ProfessorNaoExistenteException
	 * @throws BuscaListaException 
	 */
	public Professor buscar(String cpf) throws ProfessorNaoExistenteException, Exception {
		return this.controleProfessores.buscarProfessor(cpf);
	}

	/**
	 * Dada o cpf do professor, checa se ele existe
	 * 
	 * @param cpf
	 * @return true se existe; false se nao existe.
	 * @throws RepositorioException
	 */
	public boolean verificaSeProfessorExiste(String cpf) throws Exception {
		return this.controleProfessores.verificaExistenciaProfessor(cpf);
	}

	/**
	 * Metodo que altera os dados de um determinado professor. A opcao do
	 * atributo a ser alterado, o cpf do professor e a nova informacao devem ser
	 * informados. As opcoes sao: 1 - CPF 2 - Nome 3 - Titulo 4 - Lotcacao
	 * 
	 * @param op, cpf e info.
	 * @throws RepositorioException
	 * @throws ProfessorNaoExistenteException
	 * @throws BuscaListaException 
	 */
	public void alterar(Professor prof , String cpfInformadoPeloUsuario)
			throws ProfessorNaoExistenteException, Exception {
		this.controleProfessores.alterarProfessor(prof , cpfInformadoPeloUsuario);
	}

	/**
	 * Dado o cpf do professor, faz a remocao.
	 * 
	 * @param cpf
	 * @throws RepositorioException
	 * @throws ProfessorNaoExistenteException
	 * @throws BuscaListaException 
	 */
	public void remover(String cpf) throws ProfessorNaoExistenteException, Exception {
		this.controleProfessores.removerProfessor(cpf);
	}

	/**
	 * Listar os professores do repositorio
	 * 
	 * @return listaProfessores.
	 * @throws RepositorioException
	 * @throws ProfessoresNaoCadastradosException
	 */
	public List<Professor> listar() throws ProfessoresNaoCadastradosException ,Exception{
		return this.controleProfessores.listarProfessores();
	}

	/**
	 * Dado um professor, retorna todas as turmas em que ele leciona.
	 * 
	 * @param professor
	 *            .
	 * @return Lista de turmas.
	 * @throws ProfessorSemTurmaException
	 */
	public List<Turma> listarTurmaPorProfessor(Professor p)
			throws ProfessorSemTurmaException {
		if (p.getTurmas().size() == 0) {
			throw new ProfessorSemTurmaException();
		}
		List<Turma> l = new LinkedList<Turma>();
		for (Turma t : p.getTurmas()) {
			l.add(t);
		}

		return l;
	}

	/**
	 * Dados um professor e uma turma, relaciona a turma ao professor e
	 * vice-versa.
	 * 
	 * @param professor
	 *            , turma.
	 */
	public void associaProfessorTurma(Professor professor, Turma turma)  throws Exception{
		professor.getTurmas().add(turma); // Adiciona uma turma ao professor.
		turma.setProfessor(professor); // relaciona o professo a turma.
	}

	/**
	 * Dados um professor e um turma, remove a turma do professor.
	 * 
	 * @param professor
	 *            , turma.
	 */
	public void removeProfessorTurma(Professor professor, Turma turma) throws ProfessoresNaoCadastradosException,RemoverProfessorException {
		professor.getTurmas().remove(turma);
		turma.setProfessor(null);
	}

	/**
	 * Retorna a quantidade de professores inseridos no repositorio.
	 * 
	 * @return quantidadeLivros.
	 */
	public int quantidadeProfessores() throws Exception {
		return this.controleProfessores.quantidadeProfessor();
	}
}