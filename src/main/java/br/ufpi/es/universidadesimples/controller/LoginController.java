package br.ufpi.es.universidadesimples.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufpi.es.universidadesimples.entidades.Usuario;
import br.ufpi.es.universidadesimples.repositorios.UsuarioDAO;
import br.ufpi.es.universidadesimples.servicos.DadosServidor;

@Controller
public class LoginController {
	
	/**
	 * Faz a requisição do fomrulário de Login
	 * @param model Modelo que armazena informações para serem passadas para a visão
	 * @return index carrega a página incial
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String formularioLogin(Model model, HttpServletRequest request, Locale locale, HttpSession session){
		String redirecionamento = "index";
		
		if (request.getSession().getAttribute("usuarioLogado") != null){
			Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
			String dataFormatada = new DadosServidor().horaServidor(locale);
			model.addAttribute("emailUsuario", dadosUsuario.getEmail());
			model.addAttribute("serverTime", dataFormatada );
			redirecionamento = "home";
		}else {
			redirecionamento = "index";
		}
		return redirecionamento;
	}
	
	/**
	 * Efetua o login do usuário e cria uma sessão
	 * @param session sessão do usuário
	 * @param request mensagem de requisição ao servidor
	 * @param response mesnagem de retorno do servidor
	 * @param model modelo de acesso a visao
	 * @return local após validação do login
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String efetuarLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model, Locale locale){
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String redirecionamento = "";
		
		Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
		
		if (usuario == null){
			//usuario invalido e redireciona para a tela de login
			redirecionamento = "index";
			System.out.println("Usuário inválido");
		}else{
			System.out.println("Usuario logado " + usuario.getEmail());
			//e redirecina para a tela principal (home)
			session.setAttribute("usuarioLogado", usuario);
			Usuario dadosUsuario = (Usuario) session.getAttribute("usuarioLogado");
			String dataFormatada = new DadosServidor().horaServidor(locale);
			model.addAttribute("emailUsuario", dadosUsuario.getEmail());
			model.addAttribute("serverTime",dataFormatada);
			redirecionamento = "home";
		}		
		return redirecionamento;
	}
}