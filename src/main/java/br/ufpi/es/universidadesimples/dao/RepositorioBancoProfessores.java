package br.ufpi.es.universidadesimples.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufpi.es.universidadesimples.model.Professor;
import br.ufpi.es.universidadesimples.model.Turma;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunosNaoCadastradosException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessorNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessoresNaoCadastradosException;
import br.ufpi.es.universidadesimples.system.util.ConnectionManager;
/**
 * 
 * @author irvaynematheus
 *
 */
public class RepositorioBancoProfessores implements IRepositorioProfessores{
	/**
	 * Instancia do banco para professores. Caso nao exista tabela para professor, o sistema a cria
	 * @throws SQLException
	 */
	public RepositorioBancoProfessores(){
	}
	
	private void criarTabela() throws SQLException{
		String criarTabela = "CREATE TABLE IF NOT EXISTS professor ("
				+ "cpf VARCHAR(50) NOT NULL PRIMARY KEY,"
				+ "nome VARCHAR(50) NOT NULL,"
				+ "lotacao VARCHAR(50) NOT NULL,"
				+ "titulo VARCHAR(50) NOT NULL)"
				+ "ENGINE=MyISAM";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(criarTabela);
		statement.execute();
		statement.close();
	}

	/**
	 * Metodo que insere um professro
	 * 
	 * @param Professor a ser inserido
	 */
	@Override
	public void insereProfessor(Professor professor) throws Exception {
		criarTabela();
		
		String inserir = "insert into professor values (?, ?, ?, ?)";

		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(inserir);

		statement.setString(1, professor.getCpf());
		statement.setString(2, professor.getNome());
		statement.setString(3, professor.getLotacao());
		statement.setString(4, professor.getTitulo());
		
		statement.execute();
		statement.close();
		
	}

	/**
	 * Metodo que busca uma professor
	 * 
	 * @param o cpf do professor a ser encontrado
	 */
	@Override
	public Professor buscarProfessor(String cpf) throws ProfessorNaoExistenteException, SQLException {
		criarTabela();
		
		if (!(this.verificaExistenciaProfessor(cpf))) {
			throw new ProfessorNaoExistenteException();
		}
		Professor p = null;
		String buscar = "select * from professor where cpf = ?";
		
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(buscar);
		statement.setString(1, cpf);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			p = new Professor(result.getString("cpf"),
					result.getString("nome"), result.getString("lotacao"), result.getString("titulo"));
		}
		result.close();
		statement.close();
		
		return p;
	}

	/**
	 * Metodo para verificar a existencia de uma professor cadastrado
	 * 
	 * @param o Cpf do professor que esta sendo procurado
	 */
	@Override
	public boolean verificaExistenciaProfessor(String cpf) throws SQLException {
		criarTabela();
		
		String verificaexistencia = "select * from professor where cpf = ?";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(verificaexistencia);
		statement.setString(1, cpf);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo que altera as informacoes de um professor
	 * 
	 * @param o proessor e o cpf
	 */
	@Override
	public void alterarProfessor(Professor p, String cpfInformadoPeloUsuario)
			throws ProfessorNaoExistenteException, Exception  {
		criarTabela();
		
		String alterar = "update professor set nome=?, lotacao=?, titulo=? where cpf=?";

		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(alterar);
		if ((p.getNome().length() == 0) || (p.getNome().charAt(0) == ' ')
				|| (p.getTitulo().charAt(0) == ' ')
				|| (p.getLotacao().length() == 0)
				|| (p.getCpf().length() == 0)) {

			
			statement.setString(1, null);
			statement.setString(2, null);
			statement.setString(3, null);
		} else {

			
			statement.setString(1, p.getNome());
			statement.setString(2, p.getLotacao());
			statement.setString(3, p.getTitulo());
			statement.setString(4, p.getCpf());
		}

		statement.execute();
		statement.close();
		
	}

	/**
	 * Metodo que remove um professor 
	 * 
	 * @param o cpf do professor a ser removido
	 */
	@Override
	public void removerProfessor(String cpf)
			throws ProfessorNaoExistenteException, SQLException {
		criarTabela();
		
		if (this.verificaExistenciaProfessor(cpf) == false) {
			throw new ProfessorNaoExistenteException();
		}

		String remover = "delete from professor where cpf=?";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(remover);
		statement.setString(1, cpf);

		statement.execute();
		statement.close();
		
	}

	/**
	 * Metodo que lista todos os professores cadastrados
	 * 
	 * @return a lista com todos os professores
	 */
	@Override
	public List<Professor> listarProfessores() throws ProfessoresNaoCadastradosException, Exception {
		criarTabela();
		
		if (this.quantidadeProfessor() == 0) {
			throw new AlunosNaoCadastradosException();
		}

		String listar = "select * from professor";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(listar);
		ResultSet result = statement.executeQuery();

		ArrayList<Professor> professores = new ArrayList<Professor>();

		while (result.next()) {
			Professor p = new Professor(result.getString("cpf"),
					result.getString("nome"), result.getString("lotacao"), result.getString("titulo"));
			professores.add(p);
		}

		result.close();
		statement.close();
		return professores;
	}

	/**
	 * Metodo que informa a qnt de professores cadastrados no sistema
	 * 
	 * @return a qnt de professores
	 */
	@Override
	public int quantidadeProfessor() throws Exception {
		criarTabela();
		
		int cont = 0;
		String quantidade = "select COUNT(*) AS total from professor";
		PreparedStatement statement = ConnectionManager
				.reservaPreparedStatement(quantidade);
		ResultSet result = statement.executeQuery();
		while (result.next()) {

			cont = result.getInt("total");
		}
		result.close();
		statement.close();
		return cont;
	}

	@Override
	public List<Turma> listarTurmas(Professor professor) {
		// TODO Auto-generated method stub 
		return null;
	}

}
