package br.ufpi.es.system.util;
import java.util.regex.*;

/**
 * Classe que verifica um tipo de expressao regular
 * @author armandosoaressousa
 *
 */
public class ExpressaoRegular {
	//considera apenas numero N digitos
	String expressaoRegularNumeros = "[0-9]+";
	
	//considera apenas letras, acentos e cedilha
	String expressaoRegularLetras = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+";//"[a-zA-Z]+";
	
	//considrea letras e numeros sem caracteres de pontuacao
	String expressaoRegularLetrasNumeros = "[0-9A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+";

	/**
	 * Dado um padrao, verifica se o conteudo casa com esse padrao
	 * @param tipo expressaoRegularNumeros, expressaoRegularLetras, expressaoRegularLetrasNumeros
	 * @param conteudo entrada de dados em cadeia de caracteres
	 * @return true se o conteudo casa com o padrao
	 */
	boolean checa(String tipo, String conteudo){
		Pattern padrao = Pattern.compile(tipo);
		Matcher m = padrao.matcher(conteudo);
		if (m.matches()){
			return(true);
		}else{
			return(false);
		}
	}
	
	/**
	 * Checa se a entrada tem apenas numeros
	 * @param conteudo entrada de dados em cadeia de caracteres
	 * @return true se tem apenas numeros
	 */
	public boolean apenasNumeros(String conteudo){
		return(checa(expressaoRegularNumeros, conteudo));
	}
	
	/**
	 * Checa se a entrada tem apenas letras
	 * @param conteudo entrada de dados em cadeia de caracteres
	 * @return true se tem apenas letras
	 */
	public boolean apenasLetras(String conteudo){
		return(checa(expressaoRegularLetras, conteudo));
	}
	
	/**
	 * Checa se a entrada tem letras e numeros
	 * @param conteudo entrada de dados em cadeia de caracteres
	 * @return true se tem letras e numeros
	 */
	public boolean letrasNumeros(String conteudo){
		return(checa(expressaoRegularLetrasNumeros, conteudo));
	}		
}
