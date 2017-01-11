package br.ufpi.es.universidadesimples.servicos;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Classe que retorna dados do servidor local onde a aplicação roda
 * @author armandosoaressousa
 *
 */
public class DadosServidor {
	
	static final String hostName = "http://armandosousa.ddns.net";
	static final String porta = "8080";

	/**
	 * Retorna a data do Servidor onde a aplicação roda
	 * @param locale localidade
	 * @return hora do servidor local
	 */
	public String horaServidor(Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String dataFormatada = dateFormat.format(date);
		return dataFormatada;
	}	
	
}
