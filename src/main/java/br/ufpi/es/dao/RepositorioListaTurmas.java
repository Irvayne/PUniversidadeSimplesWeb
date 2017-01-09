package br.ufpi.es.dao;

import java.util.LinkedList;
import java.util.List;

import br.ufpi.es.model.Aluno;
import br.ufpi.es.model.Professor;
import br.ufpi.es.model.Turma;
import br.ufpi.es.system.exception.aluno.AlunoNaoExistenteException;

import br.ufpi.es.system.exception.turma.TurmaNaoExistenteException;
import br.ufpi.es.system.exception.turma.TurmasNaoCadastradasException;

/**
 * Classe Repositorio de Turmas em Lista
 * @author armandosoaressousa
 *
 */
public class RepositorioListaTurmas implements IRepositorioTurmas {
	private int identificador;
	private List<Turma> turmas;

	/**
	 * Construtor padrao da classe RepositorioLista Turmas. Instancia a lista
	 * para armazenar as turmas.
	 */
	public RepositorioListaTurmas() {
		identificador = 0;
		this.turmas = new LinkedList<Turma>();
	}

	/**
	 * Metodo que insere uma determinada turma na lista.
	 * 
	 * @param turma
	 */
	public void insereTurma(Turma turma) {
		turma.setIdTurma(identificador);
		identificador = identificador + 1;
		this.turmas.add(turma);
	}

	/**
	 * Metodo que busca uma turma na lista pela descricao da disciplina.
	 * 
	 * @param disciplina
	 * @return turma
	 */
	public Turma buscarTurma(String disciplina)
			throws TurmaNaoExistenteException {
		for (Turma t : turmas) {
			if (t.getDisciplina().equals(disciplina)) {
				return t;
			}
		}
		throw new TurmaNaoExistenteException();
	}
	
	/**
	 * Busca pelo identificador unico da turma
	 * @param identificador
	 * @return turma encontrada
	 * @throws TurmaNaoExistenteException
	 */
	public Turma buscarTurma(int identificador) throws TurmaNaoExistenteException{
		for (Turma t : turmas){
			if (t.getIdTurma()==identificador){
				return t;
			}
		}
		throw new TurmaNaoExistenteException();
	}

	/**
	 * Metodo que verifica se uma determinada turma esta na lista. A descricao
	 * da disciplina deve ser passada como parametro da pesquisa.
	 * 
	 * @param disciplina
	 * @return true, caso exista; false, caso nao exista.
	 */
	public boolean verificaExistenciaTurma(String disciplina) {
		for (Turma t : turmas) {
			if (t.getDisciplina().equals(disciplina)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica a existencia de uma turma pelo identificador
	 * @param identificador unico
	 * @return turma encontrada
	 */
	public boolean verificaExistenciaTurma(int identificador) {
		for (Turma t : turmas) {
			if (t.getIdTurma()==identificador) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que altera os dados de uma determinada turma. A opcao do atributo
	 * a ser alterado, a disciplina e a nova informacao devem ser informados. As
	 * opcoes sao: 1 - Departamento. 2 - Disciplina 3 - Horario
	 * 
	 * @param op
	 * @param disciplina
	 * @param info
	 * @throws RepositorioException
	 * @throws TurmaNaoExistenteException
	 */
	public void alterarTurma(int op, String disciplina, String info)
			throws TurmaNaoExistenteException {
		Turma a = this.buscarTurma(disciplina);

		switch (op) {
		case 1: // Departamento.
			a.setDepartamento(info);
			break;
		case 2: // Disciplina
			a.setDisciplina(info);
			break;
		case 3: // Horario
			a.setCargaHoraria(Integer.parseInt(info));
			break;
		}
	}
	
	/**
	 * Metodo que altera os dados de uma determinada turma e seu identificador unico. A opcao do atributo
	 * a ser alterado, a disciplina e a nova informacao devem ser informados. As
	 * opcoes sao: 1 - Departamento. 2 - Disciplina 3 - Horario
	 * 
	 * @param op
	 * @param identificador
	 * @param info
	 * @throws RepositorioException
	 * @throws TurmaNaoExistenteException
	 */
	public void alterarTurma(int op, int identificador, String info)
			throws TurmaNaoExistenteException {
		Turma a = this.buscarTurma(identificador);

		switch (op) {
		case 1: // Departamento.
			a.setDepartamento(info);
			break;
		case 2: // Disciplina
			a.setDisciplina(info);
			break;
		case 3: // Horario
			a.setCargaHoraria(Integer.parseInt(info));
			break;
		}
	}

	/**
	 * Dada uma nova turma atualiza os dados da turma encontrada
	 * @param identificador da turma original
	 * @param novaTurma
	 * @throws TurmaNaoExistenteException
	 */
	public void alterarTurma(int identificador, Turma novaTurma)
			throws TurmaNaoExistenteException {
		Turma a = this.buscarTurma(identificador);

		a.setDepartamento(novaTurma.getDepartamento());
		a.setDisciplina(novaTurma.getDisciplina());
		a.setCargaHoraria(novaTurma.getCargaHoraria());
		a.setProfessor(novaTurma.getProfessor());
		//TO DO revisar o metodo de alteracao da turma
	}	
	/**
	 * Dada a disciplina, remove a turma correspondente.
	 * 
	 * @param discplina
	 * @throws RepositorioException
	 * @throws TurmaNaoExistenteException
	 */
	public void removerTurma(String disciplina)
			throws TurmaNaoExistenteException {
		Turma t = this.buscarTurma(disciplina);
		this.turmas.remove(t);
	}
	
	/**
	 * Dado o identificador, remove a turma correspondente.
	 * 
	 * @param identificador unico da turma
	 * @throws RepositorioException
	 * @throws TurmaNaoExistenteException
	 */
	public void removerTurma(int identificador)
			throws TurmaNaoExistenteException {
		Turma t = this.buscarTurma(identificador);
		this.turmas.remove(t);
	}

	/**
	 * Metodo que retorna todos a turmas inseridas na lista.
	 * 
	 * @return Lista de turmas.
	 * @throws RepositorioException
	 * @throws TurmasNaoCadastradasException
	 */
	@Override
	public List<Turma> listarTurmas() throws TurmasNaoCadastradasException {
		if (this.quantidadeTurmas() == 0) {
			throw new TurmasNaoCadastradasException();
		}
		return (this.turmas);
	}

	/**
	 * Informa a quantidade de turmas que estao inseridas na lista.
	 * @return quantidade de turmas
	 */
	@Override
	public int quantidadeTurmas() {
		return (this.turmas.size());
	}
	
	/**
	 * Dada uma turma especifica, insere o aluno dado
	 * @param aluno
	 * @param turma
	 * @throws TurmasNaoCadastradasException
	 */
	public void insereAlunoTurma(Aluno aluno, Turma turma) throws TurmasNaoCadastradasException{
		int indiceTurmaNaLista;
		indiceTurmaNaLista = turmas.indexOf(turma);
		turmas.get(indiceTurmaNaLista).insereAluno(aluno);
	}
	public void insereProfessorTurma(Professor professor, Turma turma) throws TurmasNaoCadastradasException{
		int indiceTurmaNaLista;
		indiceTurmaNaLista = turmas.indexOf(turma);
		turmas.get(indiceTurmaNaLista).insereProfessor(professor);
	}
	
	/**
	 * Dada uma turma especifica, remove o aluno dado
	 * @param aluno
	 * @param turma
	 * @throws AlunoNaoExistenteException
	 * @throws TurmasNaoCadastradasException
	 */
	public void removeAlunoTurma(Aluno aluno, Turma turma) throws AlunoNaoExistenteException, TurmasNaoCadastradasException{
		int indiceTurmaNaLista;
		indiceTurmaNaLista = turmas.indexOf(turma);
		turmas.get(indiceTurmaNaLista).removeAluno(aluno);
	}
	
	/**
	 * Dada uma turma, lista seus alunos
	 * @param turma
	 * @return lista de alunos
	 */
	public List<Aluno> listaAlunos(Turma turma){
		return(turma.getAlunos());
	}
}