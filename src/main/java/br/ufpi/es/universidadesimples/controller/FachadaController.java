package br.ufpi.es.universidadesimples.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufpi.es.universidadesimples.entidades.Usuario;
import br.ufpi.es.universidadesimples.model.Aluno;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunosNaoCadastradosException;

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
	
	@RequestMapping(value = "/inserirAluno", method = RequestMethod.GET)
	public String inserirAluno() {
		
		return "aluno/inserirAluno";
	}
	
	@RequestMapping(value = "/inserirAluno", method = RequestMethod.POST)
	public String inserirAluno(HttpSession session, HttpServletRequest request,  Model model) {
		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String curso = request.getParameter("curso");
		
		Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
		model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		
		if(nome.equals("") || matricula.equals("") || curso.equals("")){
			model.addAttribute("informacao", "Todos os campos devem estar preenchidos!");
			return "aluno/inserirAluno";
		}
			
		Aluno aluno = new Aluno(matricula, nome, curso);
		try {
			fachada.inserirAluno(aluno);
		} catch (Exception e) {
			model.addAttribute("informacao", "Matricula invalida!");
			return "aluno/inserirAluno";
		}
		model.addAttribute("informacao", "Aluno matriculado!");
		return "aluno";
	}
	
	@RequestMapping(value = "/listarAlunos", method = RequestMethod.GET)
	public String listarAlunos(Model model) {
		try {
			model.addAttribute("alunos",fachada.listarAlunos());
		} catch (AlunosNaoCadastradosException e) {
			model.addAttribute("informacao",e.getMessage());
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
		}
		return "aluno/listarAlunos";
	}
	
	@RequestMapping(value = "/buscarAluno", method = RequestMethod.GET)
	public String listarAlunos() {
		
		return "aluno/buscarAluno";
	}
	
	@RequestMapping(value = "/buscarAluno", method = RequestMethod.POST)
	public String listarAlunos(HttpServletRequest request,  Model model) {
		String matricula = request.getParameter("matricula");
		try {
			Aluno aluno = fachada.buscarAluno(matricula);
			model.addAttribute("aluno",aluno);
		} catch (AlunoNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "aluno/buscarAluno"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "aluno/buscarAluno"; 
		}
		
		return "aluno/perfilAluno";
	}
	
	@RequestMapping(value = "/removerAluno", method = RequestMethod.GET)
	public String removerAlunos() {
		
		return "aluno/removerAluno";
	}
	
	@RequestMapping(value = "/removerAluno", method = RequestMethod.POST)
	public String removerAlunos(HttpServletRequest request,  Model model, HttpSession session) {
		String matricula = request.getParameter("matricula");
		try {
			fachada.removerAluno(matricula);
			model.addAttribute("informacao","Aluno removido com sucesso");
			Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
			model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		} catch (AlunoNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "aluno/removerAluno"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "aluno/removerAluno";
		}
		
		return "aluno";
	}
	
	@RequestMapping(value = "/alterarAluno", method = RequestMethod.GET)
	public String alterarAluno() {
		
		return "aluno/alterarAluno";
	}
	
	@RequestMapping(value = "/alterarAluno", method = RequestMethod.POST)
	public String alterarAluno(HttpServletRequest request,  Model model) {
		String matricula = request.getParameter("matricula");
		try {
			Aluno aluno = fachada.buscarAluno(matricula);
			model.addAttribute("aluno",aluno);
		} catch (AlunoNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "aluno/alterarAluno"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "aluno/alterarAluno";
		}
		
		return "aluno/alterarPerfilAluno";
	}
	
	@RequestMapping(value = "/alterarPerfilAluno", method = RequestMethod.POST)
	public String alterarPerfilAluno(HttpServletRequest request,  Model model, HttpSession session) {
		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String curso = request.getParameter("curso");
		
		if(nome.equals("") || matricula.equals("") || curso.equals("")){
			model.addAttribute("informacao", "Todos os campos devem estar preenchidos!");
			return "aluno/alterarAluno";
		}
		
		Aluno aluno = new Aluno(matricula, nome, curso);
		try {
			fachada.alterarAluno(aluno);
			model.addAttribute("informacao","Aluno alterado com sucesso");
			Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
			model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "aluno/alterarAluno";
		}
		
		return "aluno";
	}
	
	@RequestMapping(value = "/quantidadeAlunos", method = RequestMethod.GET)
	public String quantidadeAlunos(Model model) {
		try {
			model.addAttribute("informacao",fachada.quantidadeAlunos());
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "aluno";
		}
		return "aluno/quantidadeAlunos";
	}
}
