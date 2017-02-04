package br.ufpi.es.universidadesimples.controller;

import java.sql.SQLException;
import java.util.List;

import br.ufpi.es.universidadesimples.dao.IRepositorioAlunos;
import br.ufpi.es.universidadesimples.dao.IRepositorioProfessores;
import br.ufpi.es.universidadesimples.dao.IRepositorioTurmas;
import br.ufpi.es.universidadesimples.dao.RepositorioBancoAlunos;
import br.ufpi.es.universidadesimples.dao.RepositorioBancoProfessores;
import br.ufpi.es.universidadesimples.dao.RepositorioBancoTurmas;
import br.ufpi.es.universidadesimples.dao.RepositorioListaAlunos;
import br.ufpi.es.universidadesimples.dao.RepositorioListaProfessores;
import br.ufpi.es.universidadesimples.dao.RepositorioListaTurmas;
import br.ufpi.es.universidadesimples.model.Aluno;
import br.ufpi.es.universidadesimples.model.Professor;
import br.ufpi.es.universidadesimples.model.Turma;
import br.ufpi.es.universidadesimples.system.exception.BuscaListaException;
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


public class Fachada implements IFachada {
	private AlunosController meuControleAlunos;
	private ProfessoresController meuControleProfessor;
	private TurmasController meuControleTurmas;

	private IRepositorioAlunos repositorioAlunos;	
	private IRepositorioProfessores repositorioProfessores;
	private IRepositorioTurmas repositorioTurmas;

	/**
	 * Construtor padrao da classe Fachada. Ao instanciar um objeto do tipo
	 * Fachada, e definido um tipo de repositorio para armazenar os dados.
	 * @throws SQLException 
	 */
	public Fachada(){
		/*variavel que decide sobre sera usado o banco ou uma lista
		true para utilizar o banco
		false para utilizar a lista
		*/
		boolean banco = false;
		if(banco){
			this.repositorioAlunos = new RepositorioBancoAlunos();
			this.repositorioProfessores = new RepositorioBancoProfessores();
			this.repositorioTurmas = new RepositorioBancoTurmas();
		}else{
			this.repositorioAlunos = new RepositorioListaAlunos();
			this.repositorioProfessores = new RepositorioListaProfessores();
			this.repositorioTurmas = new RepositorioListaTurmas();
		}
		
		this.meuControleAlunos = new AlunosController(repositorioAlunos);
		this.meuControleProfessor = new ProfessoresController(repositorioProfessores);
		this.meuControleTurmas = new TurmasController(repositorioTurmas);
	}

	/**
	 * Insere um aluno no repositorio de alunos.
	 * 
	 * @throws RepositorioException
	 */
	@Override
	public void inserirAluno(Aluno aluno) throws Exception {
		this.meuControleAlunos.inserir(aluno);
	}

	/**
	 * Metodo altera os dados de um determinado aluno, de um repositorio de alunos. A opcao do atributo a ser
	 * alterado, a matricula do aluno e a nova informacao devem ser informados.
	 * * As opcoes sao: 1 - Matricula 2 - Nome 3 - Curso.
	 * 
	 * @param op
	 * @param matricula
	 * @param info.
	 * @throws Exception
	 * @throws RepositorioException
	 * @throws AlunoNaoExistenteException
	 */
	@Override
	public void alterarAluno(Aluno a) throws Exception {
		this.meuControleAlunos.alterarAluno(a);
	}

	/**
	 * Remove um determiando aluno do repositorio de alunos.
	 * 
	 * @param matricula
	 *           
	 * @throws RepositorioException
	 * @throws AlunoNaoExistenteException
	 */
	@Override
	public void removerAluno(String matricula)
			throws AlunoNaoExistenteException, Exception {
		this.meuControleAlunos.remover(matricula);
	}

	/**
	 * Metodo que busca uma determinado aluno pela matricula no repositorio de alunos.
	 * 
	 * @param matricula
	 * @throws RepositorioException
	 * @throws AlunoNaoExistenteException
	 */
	@Override
	public Aluno buscarAluno(String matricula)
			throws AlunoNaoExistenteException, Exception {
		return this.meuControleAlunos.buscar(matricula);
	}

	/**
	 * Metodo que lista todos os alunos cadastrados no repositorio de alunos.
	 * 
	 * @return Lista de alunos.
	 * @throws Exception
	 * @throws RepositorioException
	 * @throws AlunosNaoCadastradosException
	 */
	@Override
	public List<Aluno> listarAlunos() throws AlunosNaoCadastradosException,
			Exception {
		return this.meuControleAlunos.listar();
	}

	/**
	 * retorna a quantidade alunos inseridos no repositorio de alunos.
	 * 
	 * @throws Exception
	 */
	public int quantidadeAlunos() throws Exception {
		return this.meuControleAlunos.quantidadeAlunos();
	}

	/**
	 * Dada a matricula do aluno, checa se ele existe no repositorio de alunos
	 * 
	 * @param matricula
	 * @return true se existe
	 * @throws RepositorioException
	 */
	public boolean verificaSeExisteAluno(String matricula) throws Exception {
		return this.meuControleAlunos.verificaSeAlunoExiste(matricula);
	}

	/* ###################### PROFESSORES ############################ */
	/**
	 * Insere um professor no repositorio de professores.
	 * 
	 * @param professor
	 * @throws RepositorioException
	 */
	public void inserirProfessor(Professor professor) throws Exception {
		this.meuControleProfessor.inserir(professor);
	}

	/**
	 * Metodo que altera os dados de um determinado professor no repositorio de professores. A opcao do
	 * atributo a ser alterado, o cpf do professor e a nova informacao devem ser
	 * passados para o metodo. * As opcoes sao: 1 - CPF 2 - Nome 3 - Titulo 4 - Lotacao
	 * 
	 * @param op
	 * @param cpf
	 * @param info.
	 * @throws BuscaListaException 
	 */
	public void alterarProfessor(Professor prof , String cpfInformadoPeloUsuario)
			throws ProfessorNaoExistenteException, Exception {
		this.meuControleProfessor.alterar(prof , cpfInformadoPeloUsuario);
	}

	/**
	 * Verifica se um determinado professor esta presente no repositorio.
	 * 
	 * @param cpf
	 * @return false, se nao existe; true, caso exista.
	 * @throws RepositorioException
	 */
	public boolean verificaExistenciaProfessor(String cpf) throws Exception{
		return this.meuControleProfessor.verificaSeProfessorExiste(cpf);
	}

	/**
	 * Remove um determiando professor do repositorio de professores.
	 * 
	 * @param cpf
	 * @throws BuscaListaException 
	 */
	public void removerProfessor(String cpf)
			throws ProfessorNaoExistenteException, Exception {
		this.meuControleProfessor.remover(cpf);
	}

	/**
	 * Metodo que busca uma determinado professor pelo cpf do repositorio de professores.
	 * 
	 * @param cpf
	 * @throws BuscaListaException 
	 */
	@Override
	public Professor buscarProfessor(String cpf)
			throws ProfessorNaoExistenteException, Exception {
		return this.meuControleProfessor.buscar(cpf);
	}

	/**
	 * Metodo que lista todos os professores cadastrados no repositorio de professores.
	 */
	@Override
	public List<Professor> listarProfessores()
			throws ProfessoresNaoCadastradosException ,Exception {
		return this.meuControleProfessor.listar();
	}

	/**
	 * Dado um professor, retorna todas as turmas em que ele leciona.
	 * 
	 * @param professor
	 * @return Lista de turmas.
	 * @throws ProfessorSemTurmaException
	 */
	public List<Turma> listarTurmaPorProfessor(Professor professor)
			throws ProfessorSemTurmaException {
		return this.meuControleProfessor.listarTurmaPorProfessor(professor);
	}
	
	/**
	 * Retorna a quantidade de turmas de um professor
	 * @param professor
	 * @return quantidade de turmas
	 * @throws ProfessorSemTurmaException
	 */
	public int qtdTurmasPorProfessor(Professor professor) throws ProfessorSemTurmaException {
		List<Turma> turmasAux;
		turmasAux = listarTurmaPorProfessor(professor);
		return (turmasAux.size());
	}

	/**
	 * Dados um professor e uma turma, relaciona a turma ao professor e
	 * vice-versa.
	 * 
	 * @param professor
	 * @param turma
	 */
	public void associarProfessorTurma(Professor professor, Turma turma) throws Exception {
		this.meuControleTurmas.inserirProfessorNaTurma(professor, turma);
	}

	/**
	 * Dados um professor e um turma, remove a turma do professor.
	 * 
	 * @param professor
	 * @param turma
	 */
	public void removerProfessorTurma(Professor professor, Turma turma) throws ProfessoresNaoCadastradosException,RemoverProfessorException{
		this.meuControleProfessor.removeProfessorTurma(professor, turma);
	}

	/**
	 * retorna a quantidade de professores inseridas no repositorio
	 */
	public int quantidadeProfessores() throws Exception {
		return this.meuControleProfessor.quantidadeProfessores();
	}

	/* ###################### TURMAS ############################ */

	/**
	 * Insere uma turma no repositorio de turmas.
	 * 
	 * @param turma
	 * @throws Exception 
	 * @throws RepositorioException
	 */
	public void inserirTurma(Turma turma) throws Exception {
		this.meuControleTurmas.inserir(turma);
	}

	/**
	 * Metodo que altera os dados de uma determinada turma no repositorio de turmas. A opcao do atributo
	 * a ser alterado, a disciplina e a nova informacao devem ser informados. As
	 * opcoes sao: 1 - Departamento. 2 - Disciplina 3 - Horario 4 - Quantidade
	 * de alunos.
	 * 
	 * @param op
	 * @param identificador
	 * @param info
	 * @throws RepositorioException
	 * @throws TurmaNaoExistenteException
	 */
	public void alterarTurma(int op, int identificador, String info)
			throws TurmaNaoExistenteException, Exception {
		this.meuControleTurmas.alterar(op, identificador, info);
	}
	
	/**
	 * Dada uma turma original, faz as alteracoes com os novos dados
	 * @param identificador
	 * @param novaTurma
	 * @throws TurmaNaoExistenteException
	 */
	public void alterarTurma(int identificador, Turma novaTurma) throws TurmaNaoExistenteException, Exception{
		this.meuControleTurmas.alterar(identificador, novaTurma);
	}

	/**
	 * Dado o identificador da turma, retorna um turma.
	 * 
	 * @param identificador
	 * @return turma.
	 * @throws Exception 
	 * @throws RepositorioException
	 */
	public Turma buscarTurma(int identificador)
			throws Exception {
		return this.meuControleTurmas.buscar(identificador);
	}

	/**s
	 * Dado o identificador da turma, remove a turma.
	 * 
	 * @param identificador
	 * @throws Exception 
	 * @throws RepositorioException
	 */
	public void removerTurma(int identificador)
			throws Exception {
		this.meuControleTurmas.remover(identificador);
	}

	/**
	 * Lista todas as turmas do repositorio de turmas.
	 * 
	 * @return Lista de turmas.
	 * @throws TurmasNaoCadastradasException
	 */
	public List<Turma> listarTurmas() throws TurmasNaoCadastradasException, Exception {
		return this.meuControleTurmas.listar();
	}

	/**
	 * Matricula um aluno em determinada turma.
	 * 
	 * @param aluno
	 * @param turma
	 * @throws TurmasNaoCadastradasException 
	 */
	public void matricularAlunoTurma(Aluno a, Turma t) throws TurmasNaoCadastradasException, Exception  {
		this.meuControleTurmas.inserirAlunoNaTurma(a, t);
	}

	/**
	 * Remove um aluno de uma deterteminada turma.
	 * 
	 * @param aluno
	 * @param turma.
	 * @throws TurmasNaoCadastradasException 
	 * @throws AlunoNaoExistenteException 
	 */
	public void trancarTurmaAluno(Aluno a, Turma t) throws AlunoNaoExistenteException, TurmasNaoCadastradasException, Exception {
		this.meuControleTurmas.removerAlunoDaTurma(a, t);
	}

	/**
	 * Lista todas as turmas pertencentes a um determinado departamento.
	 * 
	 * @param departamento
	 * @return Lista de turmas.
	 * @throws RepositorioException
	 * @throws TurmasNaoCadastradasException,
	 * @throws DepartamentoSemTurmaException
	 */
	public List<Turma> listarTurmasPorDepartamento(String departamento)
			throws TurmasNaoCadastradasException,
			DepartamentoNaoExisteException , Exception{
		return this.meuControleTurmas.listarTurmasPorDepartamento(departamento);
	}

	/**
	 * Lista todos os alunos que estao em uma determinada turma.
	 * 
	 * @param turma
	 * @return Lista de alunos.
	 * @throws TurmaSemAlunoException
	 */
	public List<Aluno> listarAlunoPorTurma(Turma t)
			throws TurmaSemAlunoException, Exception {
		return this.meuControleTurmas.listarAlunoPorTurma(t);
	}

	/**
	 * Retorna a quantidade de turmas existentes no repositorio de turmas.
	 * 
	 * @return quantidade.
	 */
	public int quantidadeTurma() throws Exception {
		return this.meuControleTurmas.quantidadeTurmas();
	}
	
	/**
	 * Retorna a quantidade de turmas de um aluno
	 * @param aluno
	 * @return quantidade de turmas
	 */
	public int quantidadeTurmasAluno(Aluno aluno){
		return(0);
	}

	/**
	 * Dado o identificador de turma, checa se a turma existe
	 * 
	 * @param identificador
	 * @return true se existe; false, se nao existe.
	 * @throws Exception 
	 */
	public boolean verificaExistenciaTurma(int identificador) throws Exception {
		return this.meuControleTurmas.verificaSeTurmaExiste(identificador);
	}

	/**
	 * @throws Exception 
	 * 
	 */
	public void removerAluno(Aluno aluno) throws AlunoNaoExistenteException,Exception  {
		this.meuControleAlunos.remover(aluno.getMatricula());
	}
	
}