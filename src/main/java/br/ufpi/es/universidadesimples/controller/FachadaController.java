package br.ufpi.es.universidadesimples.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufpi.es.universidadesimples.entidades.Usuario;
import br.ufpi.es.universidadesimples.model.Aluno;
import br.ufpi.es.universidadesimples.model.Professor;
import br.ufpi.es.universidadesimples.model.Turma;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunoNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.aluno.AlunosNaoCadastradosException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessorNaoExistenteException;
import br.ufpi.es.universidadesimples.system.exception.professor.ProfessoresNaoCadastradosException;
import br.ufpi.es.universidadesimples.system.exception.turma.TurmaNaoExistenteException;

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
	public String professor(Model model, HttpSession session) {
		
		Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
		model.addAttribute("emailUsuario", dadosUsuario.getEmail());
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
	
	@RequestMapping(value = "/inserirProfessor", method = RequestMethod.GET)
	public String inserirProfessor() {
		
		return "professor/inserirProfessor";
	}
	
	@RequestMapping(value = "/inserirProfessor", method = RequestMethod.POST)
	public String inserirProfessor(HttpSession session, HttpServletRequest request,  Model model) {
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String lotacao = request.getParameter("lotacao");
		String titulo = request.getParameter("titulo");
		
		Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
		model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		
		if(nome.equals("") || cpf.equals("") || lotacao.equals("") || titulo.equals("")){
			model.addAttribute("informacao", "Todos os campos devem estar preenchidos!");
			return "professor/inserirProfessor";
		}
			
		Professor professor = new Professor(cpf, nome, lotacao, titulo);
		try {
			fachada.inserirProfessor(professor);
		} catch (Exception e) {
			model.addAttribute("informacao", e.getMessage());
			return "professor/inserirProfessor";
		}
		model.addAttribute("informacao", "Professor matriculado!");
		return "professor";
	}
	
	@RequestMapping(value = "/listarProfessores", method = RequestMethod.GET)
	public String listarProfessores(Model model) {
		
			try {
				model.addAttribute("professores",fachada.listarProfessores());
			} catch (ProfessoresNaoCadastradosException e) {
				model.addAttribute("informacao",e.getMessage());
			} catch (Exception e) {
				model.addAttribute("informacao","Erro ao processar");
			}
		
		
		return "professor/listarProfessores";
	}
	
	@RequestMapping(value = "/buscarProfessor", method = RequestMethod.GET)
	public String buscarProfessor() {
		
		return "professor/buscarProfessor";
	}
	
	
	
	@RequestMapping(value = "/buscarProfessor", method = RequestMethod.POST)
	public String buscarProfessor(HttpServletRequest request,  Model model) {
		String cpf = request.getParameter("cpf");
		try {
			Professor professor = fachada.buscarProfessor(cpf);
			model.addAttribute("professor",professor);
		} catch (ProfessorNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "professor/buscarProfessor"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "professor/buscarProfessor"; 
		}
		
		return "professor/perfilProfessor";
	}
	
	@RequestMapping(value = "/removerProfessor", method = RequestMethod.GET)
	public String removerProfessor() {
		
		return "professor/removerProfessor";
	}
	
	@RequestMapping(value = "/removerProfessor", method = RequestMethod.POST)
	public String removerProfessor(HttpServletRequest request,  Model model, HttpSession session) {
		String cpf = request.getParameter("cpf");
		try {
			fachada.removerProfessor(cpf);
			model.addAttribute("informacao","Professor removido com sucesso");
			Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
			model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		} catch (ProfessorNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "professor/removerProfessor"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "professor/removerProfessor";
		}
		
		return "professor";
	}
	
	@RequestMapping(value = "/alterarProfessor", method = RequestMethod.GET)
	public String alterarProfessor() {
		
		return "professor/alterarProfessor";
	}
	
	@RequestMapping(value = "/alterarProfessor", method = RequestMethod.POST)
	public String alterarProfessor(HttpServletRequest request,  Model model) {
		String cpf = request.getParameter("cpf");
		try {
			Professor professor = fachada.buscarProfessor(cpf);
			model.addAttribute("professor",professor);
		} catch (ProfessorNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "professor/alterarProfessor"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "professor/alterarProfessor";
		}
		
		return "professor/alterarPerfilProfessor";
	}
	
	@RequestMapping(value = "/alterarPerfilProfessor", method = RequestMethod.POST)
	public String alterarPerfilProfessor(HttpServletRequest request,  Model model, HttpSession session) {
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String lotacao = request.getParameter("lotacao");
		String titulo = request.getParameter("titulo");
		
		if(nome.equals("") || cpf.equals("") || lotacao.equals("") || titulo.equals("")){
			model.addAttribute("informacao", "Todos os campos devem estar preenchidos!");
			return "professor/alterarProfessor";
		}
		
		Professor professor = new Professor(cpf, nome, lotacao, titulo);
		try {
			fachada.alterarProfessor(professor, cpf);
			model.addAttribute("informacao","Professor alterado com sucesso");
			Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
			model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "professor/alterarProfessor";
		}
		
		return "professor";
	}
	
	@RequestMapping(value = "/quantidadeProfessores", method = RequestMethod.GET)
	public String quantidadeProfessores(Model model) {
		try {
			model.addAttribute("informacao",fachada.quantidadeProfessores());
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "professor";
		}
		return "professor/quantidadeProfessores";
	}
	
	@RequestMapping(value = "/inserirTurma", method = RequestMethod.GET)
	public String inserirTurma() {
		
		return "turma/inserirTurma";
	}
	
	@RequestMapping(value = "/inserirTurma", method = RequestMethod.POST)
	public String inserirTurma(HttpSession session, HttpServletRequest request,  Model model) {
		String departamento = request.getParameter("departamento");
		String disciplina = request.getParameter("disciplina");
		String horario = request.getParameter("horario");
		
		Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
		model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		
		try{
		Integer.parseInt(horario);
		}catch (Exception e) {
			model.addAttribute("informacao", "O campo horario deve conter valores numericos!");
			return "turma/inserirTurma";
		}
		
		if(departamento.equals("") || disciplina.equals("") || horario.equals("")){
			model.addAttribute("informacao", "Todos os campos devem estar preenchidos!");
			return "turma/inserirTurma";
		}
			
		Turma turma= new Turma(departamento, disciplina, Integer.parseInt(horario));
		try {
			fachada.inserirTurma(turma);
		} catch (Exception e) {
			model.addAttribute("informacao", e.getMessage());
			return "turma/inserirTurma";
		}
		model.addAttribute("informacao", "Turma cadastrada!");
		return "turma";
	}
	
	@RequestMapping(value = "/listarTurmas", method = RequestMethod.GET)
	public String listarTurmas(Model model) {
		
			try {
				model.addAttribute("turmas",fachada.listarTurmas());
			} catch (ProfessoresNaoCadastradosException e) {
				model.addAttribute("informacao",e.getMessage());
			} catch (Exception e) {
				model.addAttribute("informacao","Erro ao processar");
			}
		
		
		return "turma/listarTurmas";
	}
	
	@RequestMapping(value = "/buscarTurma", method = RequestMethod.GET)
	public String buscarTurma() {
		
		return "turma/buscarTurma";
	}
	
	
	
	@RequestMapping(value = "/buscarTurma", method = RequestMethod.POST)
	public String buscarTurma(HttpServletRequest request,  Model model) {
		String identificador = request.getParameter("identificador");
		
		try{
			Integer.parseInt(identificador);
			}catch (Exception e) {
				model.addAttribute("informacao", "O campo identificador deve conter valores numericos!");
				return "turma/buscarTurma";
			}
		try {
			Turma turma = fachada.buscarTurma(Integer.parseInt(identificador));
			model.addAttribute("turma",turma);
		} catch (TurmaNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "turma/buscarTurma"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "turma/buscarTurma"; 
		}
		
		return "turma/perfilTurma";
	}
	
	@RequestMapping(value = "/removerTurma", method = RequestMethod.GET)
	public String removerTurma() {
		
		return "turma/removerTurma";
	}
	
	@RequestMapping(value = "/removerTurma", method = RequestMethod.POST)
	public String removerTurma(HttpServletRequest request,  Model model, HttpSession session) {
		String identificador = request.getParameter("identificador");
		try{
			Integer.parseInt(identificador);
			}catch (Exception e) {
				model.addAttribute("informacao", "O campo identificador deve conter valores numericos!");
				return "turma/removerTurma";
			}
		try {
			fachada.removerTurma(Integer.parseInt(identificador));
			model.addAttribute("informacao","Turma removido com sucesso");
			Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
			model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		} catch (TurmaNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "turma/removerTurma"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "turma/removerTurma";
		}
		
		return "turma";
	}
	
	@RequestMapping(value = "/alterarTurma", method = RequestMethod.GET)
	public String alterarTurma() {
		
		return "turma/alterarTurma";
	}
	
	@RequestMapping(value = "/alterarTurma", method = RequestMethod.POST)
	public String alterarTurma(HttpServletRequest request,  Model model) {
		String identificador = request.getParameter("identificador");
		try{
			Integer.parseInt(identificador);
			}catch (Exception e) {
				model.addAttribute("informacao", "O campo identificador deve conter valores numericos!");
				return "turma/alterarTurma";
			}
		try {
			Turma turma = fachada.buscarTurma(Integer.parseInt(identificador));
			model.addAttribute("turma",turma);
		} catch (TurmaNaoExistenteException e) {
			model.addAttribute("informacao",e.getMessage());
			return "turma/alterarTurma"; 
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "turma/alterarTurma";
		}
		
		return "turma/alterarPerfilTurma";
	}
	
	@RequestMapping(value = "/alterarPerfilTurma", method = RequestMethod.POST)
	public String alterarPerfilTurma(HttpServletRequest request,  Model model, HttpSession session) {
		String identificador = request.getParameter("identificador");
		String departamento = request.getParameter("departamento");
		String disciplina = request.getParameter("disciplina");
		String horario = request.getParameter("horario");
		
		Usuario dadosUsuario = (Usuario)session.getAttribute("usuarioLogado");
		model.addAttribute("emailUsuario", dadosUsuario.getEmail());
		
		try{
			Integer.parseInt(identificador);
			}catch (Exception e) {
				model.addAttribute("informacao", "O campo horario deve conter valores numericos!");
				return "turma/alterarPerfilTurma";
			}
		
		try{
		Integer.parseInt(horario);
		}catch (Exception e) {
			model.addAttribute("informacao", "O campo horario deve conter valores numericos!");
			return "turma/alterarPerfilTurma";
		}
		
		if(departamento.equals("") || disciplina.equals("") || horario.equals("")){
			model.addAttribute("informacao", "Todos os campos devem estar preenchidos!");
			return "turma/alterarPerfilTurma";
		}
			
		Turma turma= new Turma(departamento, disciplina, Integer.parseInt(horario));
		
		try {
			fachada.alterarTurma(Integer.parseInt(identificador), turma);
			model.addAttribute("informacao","Turma alterado com sucesso");
			
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "professor/alterarProfessor";
		}
		
		return "turma";
	}
	
	@RequestMapping(value = "/quantidadeTurmas", method = RequestMethod.GET)
	public String quantidadeTurmas(Model model) {
		try {
			model.addAttribute("informacao",fachada.quantidadeTurma());
		} catch (Exception e) {
			model.addAttribute("informacao","Erro no processamento");
			return "turma";
		}
		return "turma/quantidadeTurmas";
	}
}
