package br.ufpi.es.universidadesimples.repositorios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpi.es.universidadesimples.entidades.Usuario;


public class UsuarioDAO {

	/**
	 * Hashmap de usuarios
	 */
	private final static Map<String, Usuario> USUARIOS = new HashMap<>();
	static {
		USUARIOS.put("guilherme.silveira@alura.com.br", new Usuario("guilherme","guilherme.silveira@alura.com.br","silveira"));
		USUARIOS.put("rodrigo.turini@alura.com.br", new Usuario("rodrigo","rodrigo.turini@alura.com.br","turini"));
		USUARIOS.put("armando@ufpi.edu.br", new Usuario("armando","armando@ufpi.edu.br", "armando"));
		USUARIOS.put("maria@gmail.com", new Usuario("maria", "maria@gmail.com","maria"));
		USUARIOS.put("irvaynematheus@gmail.com", new Usuario("irvayne", "irvaynematheus@gmail.com","123456"));
		
	}

	/**
	 * Busca um usuario
	 * @param email email passado
	 * @param senha senha passada
	 * @return usuario Usuario
	 */
	public Usuario buscaPorEmailESenha(String email, String senha) {
		if (!USUARIOS.containsKey(email))
			return null;

		Usuario usuario = USUARIOS.get(email);
		if (usuario.getSenha().equals(senha))
			return usuario;
		
		return null;
	}
	
	/**
	 * Metodo publico para adicionar usuario
	 * @param usuario usuario
	 */
	public void adiciona(Usuario usuario){
		insere(usuario);
	}
	
	/**
	 * Metodo estatico para acessar o hashmap
	 * @param usuario usuario
	 */
	private static void insere(Usuario usuario){
		USUARIOS.put(usuario.getEmail(), usuario);
	}
	
	/**
	 * Retorna uma colecao de usuarios
	 * @param nome nome do usuario
	 * @return usuario(s) que casa com o padrao ou a colecao completa de usuarios caso o parametro seja vazio
	 */
	public Collection<Usuario> buscaPorNome(String nome) {
		if (nome == null)
			return USUARIOS.values();
		
		List<Usuario> similares = new ArrayList<>();
		for (Usuario usuario : USUARIOS.values()) {
			if (usuario.getNome().toLowerCase().contains(nome.toLowerCase()))
				similares.add(usuario);
		}
		return similares;
	}

}
