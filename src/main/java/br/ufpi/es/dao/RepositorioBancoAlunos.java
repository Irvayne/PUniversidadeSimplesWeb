package br.ufpi.es.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpi.es.model.Aluno;
import br.ufpi.es.model.Turma;
import br.ufpi.es.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.system.exception.aluno.AlunosNaoCadastradosException;
import br.ufpi.es.system.util.ConnectionManager;

/**
 * @author Jordao
 */
public class RepositorioBancoAlunos implements IRepositorioAlunos {
/**
 * Instancia das configuracoes do banco. Caso não exista as tabelas, o proprio sistema as cria.
 * @throws SQLException
 */
	public RepositorioBancoAlunos(){	}

	private void criarTabelas() throws SQLException{
		String criarTabela = "CREATE TABLE IF NOT EXISTS aluno ("
				+ "matricula VARCHAR(50) NOT NULL PRIMARY KEY,"
				+ "nome VARCHAR(50) NOT NULL,"
				+ "curso VARCHAR(50) NOT NULL)"
				+ "ENGINE=MyISAM";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(criarTabela);
		statement.execute();
		statement.close();
	}
	/**
	 * M�todo que insere um aluno no banco.
	 * 
	 * @param aluno
	 *            .
	 */
	@Override
	public void insereAluno(Aluno aluno) throws Exception {
		criarTabelas();
		
		String inserir = "insert into aluno values (?, ?, ?)";

		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(inserir);

		statement.setString(1, aluno.getMatricula());
		statement.setString(2, aluno.getNome());
		statement.setString(3, aluno.getCurso());

		statement.execute();
		statement.close();
	}

	/**
	 * M�todo que busca um aluno no banco atrav�s de sua matr�cula.
	 * 
	 * @param matricula
	 * @return aluno
	 */
	@Override
	public Aluno buscarAluno(String matricula)
			throws AlunoNaoExistenteException, SQLException {
		criarTabelas();
		
		if (!(this.verificaExistenciaAluno(matricula))) {
			throw new AlunoNaoExistenteException();
		}
		Aluno a = null;
		String buscar = "select * from aluno where matricula = ?";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(buscar);
		statement.setString(1, matricula);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			a = new Aluno(result.getString("matricula"),
					result.getString("nome"), result.getString("curso"));
			
		}
		result.close();
		statement.close();
		return a;
	}

	/**
	 * M�todo que verifica a exist�ncia de um aluno no banco atrav�s de sua
	 * matr�cula.
	 * 
	 * @param matricula
	 * @return true se existir o aluno.
	 * @return false se n�o existir.
	 */
	@Override
	public boolean verificaExistenciaAluno(String matricula)
			throws SQLException {
		criarTabelas();
		
		String verificaexistencia = "select * from aluno where matricula = ?";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(verificaexistencia);
		statement.setString(1, matricula);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			return true;
		}
		
		return false;
	}

	@Override
	/**
	 * M�todo que altera as informa��es de um aluno.
	 * @param aluno
	 */
	public void alterarAluno(Aluno a) throws SQLException {
		criarTabelas();
		
		String alterar = "update aluno set nome=?, curso=? where matricula=?";

		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(alterar);
		if ((a.getNome().length() == 0) || (a.getNome().charAt(0) == ' ')
				|| (a.getCurso().charAt(0) == ' ')
				|| (a.getCurso().length() == 0)
				|| (a.getMatricula().length() == 0)) {

			statement.setString(1, null);
			statement.setString(2, null);
			
			
		} else {

			statement.setString(1, a.getNome());
			statement.setString(2, a.getCurso());
			statement.setString(3, a.getMatricula());
			
		}

		statement.execute();
		statement.close();
	}

	/**
	 * Metodo que remove um aluno do banco de dados.
	 * 
	 * @param matricula
	 */
	@Override
	public void removerAluno(String matricula)
			throws AlunoNaoExistenteException, SQLException {
		criarTabelas();
		
		if (this.verificaExistenciaAluno(matricula) == false) {
			throw new AlunoNaoExistenteException();
		}

		String remover = "delete from aluno where matricula=?";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(remover);
		statement.setString(1, matricula);

		statement.execute();
		statement.close();
	}
	/**
	 * Metodo que remove um aluno
	 * 
	 * @param Objeto aluno a ser removido
	 */
	public void removerAluno(Aluno aluno)
			throws AlunoNaoExistenteException, Exception {
		criarTabelas();
		
		if (this.verificaExistenciaAluno(aluno.getMatricula()) == false) {
			throw new AlunoNaoExistenteException();
		}

		String remover = "delete from aluno where matricula=?";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(remover);
		statement.setString(1, aluno.getMatricula());

		statement.execute();
		statement.close();
	}

	/**
	 * M�todo que retorna uma lista com todos os alunos cadastrados no banco.
	 * 
	 * @return alunos
	 */
	@Override
	public ArrayList<Aluno> listarAlunos()
			throws AlunosNaoCadastradosException, SQLException {
		criarTabelas();
		
		if (this.quantidadeAlunos() == 0) {
			throw new AlunosNaoCadastradosException();
		}

		String listar = "select * from aluno";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(listar);
		ResultSet result = statement.executeQuery();

		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		while (result.next()) {
			Aluno aluno = new Aluno(result.getString("matricula"),
					result.getString("nome"), result.getString("curso"));
			alunos.add(aluno);
		}

		result.close();
		statement.close();
		return alunos;
	}

	/**
	 * M�todo que retorna a quantidade de alunos cadastrados no banco.
	 * 
	 * @return cont
	 */
	@Override
	public int quantidadeAlunos() throws SQLException {
		criarTabelas();
		
		int cont = 0;
		String quantidade = "select COUNT(*) AS total from aluno";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(quantidade);
		ResultSet result = statement.executeQuery();
		// statement.setInt(arg0, arg1);
		while (result.next()) {

			cont = result.getInt("total");
		}
		result.close();
		statement.close();
		return cont;
	}

	@Override
	public List<Turma> listarTurmas(Aluno aluno) {
		
		return null;
	}

}
