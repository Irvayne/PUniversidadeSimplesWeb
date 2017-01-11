package br.ufpi.es.universidadesimples.controller;

import java.util.List;

import br.ufpi.es.universidadesimples.dao.IRepositorioAlunos;
import br.ufpi.es.universidadesimples.model.Aluno;
import br.ufpi.es.universidadesimples.model.Turma;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunosNaoCadastradosException;

public class AlunosController {
	private IRepositorioAlunos controleAlunos;

	/**
	 * Instancia o controlador de acordo com o tipo de repositorio
	 * 
	 * @param tipo
	 */
	public AlunosController(IRepositorioAlunos tipo) {
		this.controleAlunos = tipo;
	}

	/**
	 * Insere um aluno no repositorio
	 * 
	 * @param aluno
	 * @throws RepositorioException
	 */
	public void inserir(Aluno aluno) throws Exception{
		this.controleAlunos.insereAluno(aluno);
	}

	/**
	 * Dada a matricula retorna o Aluno
	 * 
	 * @param matricula
	 * @return aluno
	 * @throws RepositorioException
	 */
	public Aluno buscar(String matricula) throws AlunoNaoExistenteException,
			Exception {
		return this.controleAlunos.buscarAluno(matricula);
	}

	/**
	 * Dada a matricula do aluno, checa se ele existe
	 * 
	 * @param matricula
	 * @return true se existe
	 * @throws RepositorioException
	 */
	public boolean verificaSeAlunoExiste(String matricula) throws Exception {
		return this.controleAlunos.verificaExistenciaAluno(matricula);
	}

	/**
	 * Dado um aluno faz a atualizacao com os novos dados. Opcoo do dado campo a
	 * ser alterado, a matricula do aluno e a nova informacao. As opcoes sao: 1
	 * - Matricula 2 - Nome 3 - Curso.
	 * 
	 * @param op
	 * @param matricula
	 * @param info
	 * @throws RepositorioException
	 */

	public void alterarAluno(Aluno a) throws Exception {
		this.controleAlunos.alterarAluno(a);
	}

	/**
	 * Dada a matricula do aluno, faz a remocao.
	 * 
	 * @param matricula
	 * @throws RepositorioException
	 */
	public void remover(String matricula) throws AlunoNaoExistenteException, Exception {
		this.controleAlunos.removerAluno(matricula);
	}
	
	/**
	 * Remove um aluno do repositorio de alunos.
	 * @param aluno
	 * @throws Exception 
	 * @throws AlunoNaoExistenteException 
	 */
	public void remover(Aluno aluno) throws AlunoNaoExistenteException, Exception{
		this.controleAlunos.removerAluno(aluno);
	}

	/**
	 * Listar os alunos do repositorio
	 * 
	 * @return lista de alunos
	 * @throws RepositorioException
	 */
	public List<Aluno> listar() throws AlunosNaoCadastradosException, Exception {
		return this.controleAlunos.listarAlunos();
	}

//	/**
//	 * Realiza a matricula de aluno em uma determinada turma.
//	 * 
//	 * @param aluno
//	 * @param turma
//	 */
//	public void matricularAlunoTurma(Aluno a, Turma t) {
//		t.getAlunos().add(a); // Adicionando aluno na turma.
//		a.getTurmas().add(t);// Adicionando turma ao aluno.
//	}
//
//	/**
//	 * Remove um determinado aluno de um turma.
//	 * 
//	 * @param aluno
//	 * @param turma
//	 */
//	public void trancarTurmaAluno(Aluno a, Turma t) throws AlunoNaoExistenteException, TurmasNaoCadastradasException {
//		a.getTurmas().remove(t); // Removendo a turma de um aluno.
//		t.getAlunos().remove(a); // Removendo um aluno da turma.
//	}

	/**
	 * Retorna a quantidade de aluno inseridos no repositorio.
	 * 
	 * @return quantidadeAlunos
	 */
	public int quantidadeAlunos() throws Exception {
		return this.controleAlunos.quantidadeAlunos();
	}
	
	/**
	 * Dado um aluno, retorna suas turmas
	 * @param aluno
	 * @return lista de turmas
	 */
	public List<Turma> listarTurmas(Aluno aluno){
		return this.controleAlunos.listarTurmas(aluno);
	}
}