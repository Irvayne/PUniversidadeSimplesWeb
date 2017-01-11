package br.ufpi.es.universidadesimples.dao;

import java.util.LinkedList;
import java.util.List;

import br.ufpi.es.universidadesimples.model.Aluno;
import br.ufpi.es.universidadesimples.model.Turma;
import br.ufpi.es.universidadesimples.system.exception.BuscaListaException;
import br.ufpi.es.universidadesimples.system.exception.InserirListaException;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlterarAlunoListaException;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunosNaoCadastradosException;
import br.ufpi.es.universidadesimples.system.exception.aluno.RemoverAlunoException;
import br.ufpi.es.universidadesimples.system.exception.aluno.VerificarExistenciaAlunoListaException;

public class RepositorioListaAlunos implements IRepositorioAlunos {
	private List<Aluno> alunos;
	private int idAluno;

	/**
	 * Instancia a lista para armazenar os alunos.
	 */
	public RepositorioListaAlunos() {
		this.alunos = new LinkedList<Aluno>();
		this.idAluno = 0;
		System.out.println("Instancia de lista");
	}

	/**
	 * Metodo que insere um determinado aluno na lista.
	 * 
	 * @param aluno
	 *            .
	 * @throws VerificarExistenciaAlunoListaException 
	 */
	@Override
	public void insereAluno(Aluno aluno) throws InserirListaException, VerificarExistenciaAlunoListaException {
		if ((aluno.getMatricula() == null) || (aluno.getNome() == null)
				|| (aluno.getCurso() == null)) {
			throw new InserirListaException();
		}
		if (!verificaExistenciaAluno(aluno.getMatricula())){
			
			this.idAluno = idAluno + 1;
			this.alunos.add(aluno);
		}else{
			throw new VerificarExistenciaAlunoListaException();
		}
	}

	/**
	 * Metodo que busca um aluno na lista pela maticula. A matricula do aluno
	 * deve ser informada.
	 * 
	 * @param matricula
	 *            
	 * @return aluno.
	 */
	@Override
	public Aluno buscarAluno(String matricula)
			throws AlunoNaoExistenteException, BuscaListaException {
		if (matricula == null) {
			throw new BuscaListaException();
		}
		for (Aluno a : alunos) {
			if (a.getMatricula().equals(matricula)) {
				return a;
			}
		}
		throw new AlunoNaoExistenteException();
	}

	/**
	 * Metodo que verifica se um determinado aluno esta na lista. A matricula do
	 * aluno deve ser passada como parametro da pesquisa.
	 * 
	 * @param matricula
	 *            
	 * @return boolean
	 * @throws VerificarExistenciaAlunoListaException
	 */
	@Override
	public boolean verificaExistenciaAluno(String matricula)
			throws VerificarExistenciaAlunoListaException {
		if (matricula == null) {
			throw new VerificarExistenciaAlunoListaException();
		}
		for (Aluno a : alunos) {
			if (a.getMatricula().equals(matricula)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Altera um aluno. A opcao do atributo a ser alterado, a matricula do aluno
	 * e a nova informacao devem ser passadas para o metodo. As opcoes sao: 1 -
	 * Matricula 2 - Nome 3 - Curso.
	 * 
	 * @param op
	 *            , matricula, info.
	 * @throws BuscaListaException
	 */
	@Override
	public void alterarAluno(Aluno a) throws AlterarAlunoListaException {

		if ((a.getNome().length() == 0) || (a.getNome().charAt(0) == ' ')
				|| (a.getCurso().charAt(0) == ' ')
				|| (a.getCurso().length() == 0)
				|| (a.getMatricula().length() == 0) ) {
			throw new AlterarAlunoListaException();
		}
		for (Aluno a2 : this.alunos) {
			if (a2.getMatricula().equals(a.getMatricula())) {
				a2.setNome(a.getNome());
				a2.setCurso(a.getCurso());
				a2.setMatricula(a.getMatricula());
				break;
			}
		}
	}

	/**
	 * Remove um determinado aluno da lista. A matricula do aluno deve ser
	 * informada.
	 * 
	 * @param matricula
	 * @throws BuscaListaException
	 */
	@Override
	public void removerAluno(String matricula)
			throws AlunoNaoExistenteException, RemoverAlunoException,
			BuscaListaException {
		Aluno a = this.buscarAluno(matricula);
		this.alunos.remove(a);
	}
	
	/**
	 * Remove um dado aluno no repositorio de alunos
	 * @param aluno
	 */
	public void removerAluno(Aluno aluno){
		this.alunos.remove(aluno);
	}

	/**
	 * Metodo que retorna todos os alunos inseridos na lista.
	 * 
	 * @return alunos
	 */
	@Override
	public List<Aluno> listarAlunos() throws AlunosNaoCadastradosException {
		if (this.quantidadeAlunos() == 0) {
			throw new AlunosNaoCadastradosException();
		}
		return (this.alunos);
	}

	/**
	 * Informa a quantidade de alunos que estao inseridos na lista.
	 * 
	 * @return 	quantidade
	 */
	@Override
	public int quantidadeAlunos() {
		return this.alunos.size();
	}
	
	/**
	 * Dado um aluno, retorna suas turmas
	 * @param aluno
	 * @return lista de turmas
	 */
	public List<Turma> listarTurmas(Aluno aluno){
		return(aluno.getTurmas());
	}
}
