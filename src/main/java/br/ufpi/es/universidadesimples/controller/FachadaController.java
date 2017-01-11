package br.ufpi.es.universidadesimples.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufpi.es.universidadesimples.entidades.Usuario;

@Controller
public class FachadaController {
	
	Fachada fachada = new Fachada();

	@RequestMapping(value = "/aluno", method = RequestMethod.GET)
	public String aluno(Model model, HttpSession session) {
		
		Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
		model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		return "aluno";
	}
	
	@RequestMapping(value = "/professor", method = RequestMethod.GET)
	public String professor() {
		
		return "professor";
	}
	
	@RequestMapping(value = "/turma", method = RequestMethod.GET)
	public String turma() {
		
		return "turma";
	}
}
