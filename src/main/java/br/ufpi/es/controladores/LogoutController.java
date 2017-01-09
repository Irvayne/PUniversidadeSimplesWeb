package br.ufpi.es.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufpi.es.entidades.Usuario;

@Controller
public class LogoutController {

	@RequestMapping(value="/deslogado", method=RequestMethod.GET)
	public String deslogado(HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		session.removeAttribute("usuarioLogado");
		System.out.println("usuário " + usuario.getEmail() + " - " + "deslogado");
		return "deslogado";
	}
	
}
