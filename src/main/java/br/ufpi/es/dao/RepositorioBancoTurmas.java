package br.ufpi.es.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpi.es.model.Aluno;
import br.ufpi.es.model.Professor;
import br.ufpi.es.model.Turma;
import br.ufpi.es.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.system.exception.turma.TurmaNaoExistenteException;
import br.ufpi.es.system.exception.turma.TurmasNaoCadastradasException;
import br.ufpi.es.system.util.ConnectionManager;

/**
 * 
 * @author irvaynematheus
 *
 */
public class RepositorioBancoTurmas implements IRepositorioTurmas {
	/**
	 * Instancia da classe. Ao ser instanciado, caso nao exista as tabalas
	 * criadas, o sistema as cria.
	 * 
	 * @throws SQLException
	 */
	public RepositorioBancoTurmas() {

	}

	private void criarTabela() throws SQLException {
		// criar tabela turma
		String criarTabela = "CREATE TABLE IF NOT EXISTS turma (id INT(5) AUTO_INCREMENT PRIMARY KEY,"
				+ "departamento VARCHAR(50) NOT NULL," + "disciplina VARCHAR(50) NOT NULL,"
				+ "carga_horaria INT(5) NOT NULL," + "cpfProfessor INT(5))" + "ENGINE=MyISAM";
		PreparedStatement statement = ConnectionManager.reservaPreparedStatement(criarTabela);
		statement.execute();
		// Criar tabela para a Relacao do Aluno com a Turma
		String criarRelacao = "CREATE TABLE IF NOT EXISTS relacaoAlunoTurma (id INT(5) AUTO_INCREMENT PRIMARY KEY,"
				+ "matriculaAluno VARCHAR(50) NOT NULL," + "idTurma INT(5) NOT NULL)" + "ENGINE=MyISAM";
		statement = ConnectionManager.reservaPreparedStatement(criarRelacao);
		statement.execute();
		statement.close();
	}

	/**
	 * Metodo para inserir uma turma
	 * 
	 * @param Objeto
	 *            turma a ser inserida
	 */
	@Override
	public void insereTurma(Turma turma) throws SQLException {
		criarTabela();
		
		String inserir = "insert into turma values (0, ?, ?, ?, null)";

		PreparedStatement statement = ConnectionManager.reservaPreparedStatement(inserir);

		statement.setString(1, turma.getDepartamento());
		statement.setString(2, turma.getDisciplina());
		statement.setInt(3, turma.getCargaHoraria());

		statement.execute();
		statement.close();

	}

	/**
	 * Metodo que busca uma turma cadastrada
	 * 
	 * @param o
	 *            identificador da turma
	 */
	@Override
	public Turma buscarTurma(int identificador) throws TurmaNaoExistenteException, Exception {
		criarTabela();
		
		if (!(this.verificaExistenciaTurma(identificador))) {
			throw new TurmaNaoExistenteException();
		}
		RepositorioBancoProfessores dao = new RepositorioBancoProfessores();
		Turma t = null;
		String buscar = "select * from turma where id = ?";
		PreparedStatement statement = ConnectionManager.reservaPreparedStatement(buscar);
		statement.setInt(1, identificador);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			t = new Turma(result.getString("departamento"), result.getString("disciplina"),
					result.getInt("carga_horaria"));
			t.setIdTurma(result.getInt("id"));
			t.setAlunos(this.listaAlunos(t));

			if (result.getString("cpfProfessor") != null) {
				t.setProfessor(dao.buscarProfessor(result.getString("cpfProfessor")));
			}
		}
		result.close();
		statement.close();
		return t;
	}

	/**
	 * Metodo que verifica a existencia de uma turma cadastrada no sistema
	 * 
	 * @param o
	 *            identificador da turma a ser buscada
	 */
	@Override
	public boolean verificaExistenciaTurma(int identificador) throws Exception {
		criarTabela();
		
		String verificaexistencia = "select * from turma where id = ?";
		PreparedStatement statement = ConnectionManager.reservaPreparedStatement(verificaexistencia);
		statement.setInt(1, identificador);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			return true;
		}

		return false;
	}

	/**
	 * Metodo que altera as informacoes de uma turma
	 * 
	 * @param as
	 *            alteracoes
	 * @exception Exception
	 */
	@Override
	public void alterarTurma(int op, int identificador, String info) throws Exception {
		criarTabela();
		
		Turma a = this.buscarTurma(identificador);
		String alterar = "";
		switch (op) {
		case 1: // Departamento.
			a.setDepartamento(info);
			alterar = "update turma set departamento = " + a.getDepartamento() + " where id = " + identificador;
			break;
		case 2: // Disciplina
			a.setDisciplina(info);
			alterar = "update turma set disciplina = " + a.getDisciplina() + " where id = " + identificador;
			break;
		case 3: // Horario
			a.setCargaHoraria(Integer.parseInt(info));
			alterar = "update turma set carga_horaria = " + a.getCargaHoraria() + " where id = " + identificador;
			break;
		}
		if (alterar.equals("")) {
			PreparedStatement statement = ConnectionManager.reservaPreparedStatement(alterar);
			statement.execute();
			statement.close();
		}
	}

	/**
	 * Metodo que remove uma turma
	 * 
	 * @param o
	 *            identificador da turma a ser removida
	 * 
	 */
	@Override
	public void removerTurma(int identificador) throws TurmaNaoExistenteException, Exception {
		criarTabela();
		
		if (this.verificaExistenciaTurma(identificador) == false) {
			throw new TurmaNaoExistenteException();
		}

		String remover = "delete from turma where id=?";
		PreparedStatement statement = ConnectionManager.reservaPreparedStatement(remover);
		statement.setInt(1, identificador);

		statement.execute();
		statement.close();

	}

	/**
	 * Metodo que lista as turmas cadastrada
	 * 
	 * @return Lista com as turmas
	 */
	@Override
	public List<Turma> listarTurmas() throws TurmasNaoCadastradasException, Exception {
		criarTabela();
		
		if (this.quantidadeTurmas() == 0) {
			throw new TurmasNaoCadastradasException();
		}

		String listar = "select * from turma";
		PreparedStatement statement = ConnectionManager.reservaPreparedStatement(listar);
		ResultSet result = statement.executeQuery();

		ArrayList<Turma> turmas = new ArrayList<Turma>();

		while (result.next()) {
			Turma turma = new Turma(result.getString("departamento"), result.getString("disciplina"),
					result.getInt("carga_horaria"));

			turma.setIdTurma(result.getInt("id"));
			turma.setProfessor(new Professor(result.getString("cpfProfessor"), null, null, null));
			turmas.add(turma);
		}

		result.close();
		statement.close();
		return turmas;
	}

	/**
	 * Metodo que informa a quantidade de turmas cadastradas
	 * 
	 * @return a quantidade de turmas
	 */
	@Override
	public int quantidadeTurmas() throws Exception {
		criarTabela();
		
		int cont = 0;
		String quantidade = "select COUNT(*) AS total from turma";
		PreparedStatement statement = ConnectionManager.reservaPreparedStatement(quantidade);
		ResultSet result = statement.executeQuery();
		// statement.setInt(arg0, arg1);
		while (result.next()) {

			cont = result.getInt("total");
		}
		result.close();
		statement.close();
		return cont;
	}

	/**
	 * Metodo que insere um aluno em uma turma
	 * 
	 * @param O
	 *            aluno e a turma que deseja realizar a relacao
	 */
	@Override
	public void insereAlunoTurma(Aluno aluno, Turma turma) throws TurmasNaoCadastradasException, Exception {
		criarTabela();
		
		if (this.verificaExistenciaTurma(turma.getIdTurma())) {
			String inserir = "insert into relacaoAlunoTurma values (0, ?, ?)";

			PreparedStatement statement = ConnectionManager.reservaPreparedStatement(inserir);

			statement.setString(1, aluno.getMatricula());
			statement.setInt(2, turma.getIdTurma());

			statement.execute();
			statement.close();
		} else {
			throw new TurmasNaoCadastradasException();
		}

	}

	/**
	 * Metodo que remove uma aluno de uma turma
	 * 
	 * @param o
	 *            aluno e a turma que deseja desfazer a relacao
	 */
	@Override
	public void removeAlunoTurma(Aluno aluno, Turma turma)
			throws AlunoNaoExistenteException, TurmasNaoCadastradasException, Exception {
		criarTabela();
		
		RepositorioBancoAlunos dao = new RepositorioBancoAlunos();

		if (this.verificaExistenciaTurma(turma.getIdTurma())) {
			if (dao.verificaExistenciaAluno(aluno.getMatricula())) {
				String delete = "delete from relacaoAlunoTurma where matriculaAluno = ? and idTurma = ?";
				PreparedStatement statement = ConnectionManager.reservaPreparedStatement(delete);

				statement.setString(1, aluno.getMatricula());
				statement.setInt(2, turma.getIdTurma());

				statement.execute();
				statement.close();
			} else {
				throw new AlunoNaoExistenteException();
			}
		} else {
			throw new TurmasNaoCadastradasException();
		}

	}

	/**
	 * Metodo que lista os alunos de uma turma
	 * 
	 * @param A
	 *            turma que deseja obter tal informacao
	 * @return A lista com os alunos
	 */
	@Override
	public List<Aluno> listaAlunos(Turma turma) throws Exception {
		criarTabela();
		
		RepositorioBancoAlunos dao = new RepositorioBancoAlunos();
		String listar = "select * from relacaoAlunoTurma where idTurma = ?";
		PreparedStatement statement = ConnectionManager.reservaPreparedStatement(listar);
		statement.setInt(1, turma.getIdTurma());

		ResultSet result = statement.executeQuery();

		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		while (result.next()) {
			Aluno aluno = dao.buscarAluno(result.getString("matriculaAluno"));
			alunos.add(aluno);
		}

		result.close();
		statement.close();
		return alunos;
	}

	/**
	 * Metodo que insere uma professor a uma turma
	 * 
	 * @param O
	 *            professor e a turma que deseja criar a relacao
	 */
	@Override
	public void insereProfessorTurma(Professor professor, Turma turma) throws TurmasNaoCadastradasException, Exception {
		criarTabela();
		
		if (this.verificaExistenciaTurma(turma.getIdTurma())) {
			String inserir = "update turma set cpfProfessor = ? where id =?";
			PreparedStatement statement = ConnectionManager.reservaPreparedStatement(inserir);

			statement.setString(1, professor.getCpf());
			statement.setInt(2, turma.getIdTurma());

			statement.execute();
			statement.close();
		} else {
			throw new TurmasNaoCadastradasException();
		}

	}

	/**
	 * Metodo que altera as informacoes de uma turm
	 * 
	 * @paramO id da turma a ser alterada e as novas informacoes dessa turma
	 */
	@Override
	public void alterarTurma(int identificador, Turma novaTurma) throws Exception {
		criarTabela();
		
		if (this.verificaExistenciaTurma(identificador)) {
			String alterar = "update turma set departamento=?, disciplina=?, cargaHoraria=? where id=?";

			PreparedStatement statement = ConnectionManager.reservaPreparedStatement(alterar);

			statement.setString(1, novaTurma.getDepartamento());
			statement.setString(2, novaTurma.getDisciplina());
			statement.setInt(3, novaTurma.getCargaHoraria());

			statement.execute();
			statement.close();
		} else {
			throw new TurmasNaoCadastradasException();
		}
	}

}
