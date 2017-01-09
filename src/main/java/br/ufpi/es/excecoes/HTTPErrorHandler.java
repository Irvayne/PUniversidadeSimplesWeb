package br.ufpi.es.excecoes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTTPErrorHandler {
	
	@RequestMapping(value="/400")
	public String erro400(){
		System.out.println("custom error 400 handler");
		return("/400");
	}

	@RequestMapping(value="/404")
	public String erro404(){
		System.out.println("custom error 404 handler");
		return("/404");
	}
	
	@RequestMapping(value="/500")
	public String erro500(){
		System.out.println("custom error 500 handler");
		return("/500");
	}
}

