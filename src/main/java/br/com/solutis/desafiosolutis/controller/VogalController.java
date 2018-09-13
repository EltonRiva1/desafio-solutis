package br.com.solutis.desafiosolutis.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.solutis.desafiosolutis.model.Vogal;

/**
 * 
 * @author notle
 *
 */

@RestController
@RequestMapping("/vogal")
public class VogalController {
	private static final String VOGAIS = "AEIOU";

	@RequestMapping(value = "{input}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Vogal realizarDesafioVogal(@PathVariable("input") String entrada) {
		long tempoInicial = System.currentTimeMillis();
		Vogal vogal = desafio(entrada);
		long tempoFinal = System.currentTimeMillis();
		vogal.setTempoTotal(String.valueOf((tempoFinal - tempoInicial)) + "ms");
		return vogal;
	}

	/**
	 * 
	 * @param entrada
	 * @return
	 */
	private Vogal desafio(String entrada) {
		int pos = 0;
		Character result = null;
		char[] vogais = new char[] { 'a', 'e', 'i', 'o', 'u' };
		int[] vogaisCont = new int[] { 0, 0, 0, 0, 0 };
		for (int i = 0; i < entrada.length(); i++) {
			char index = entrada.toLowerCase().toCharArray()[i];
			int vogalSelecionada = -1;
			for (int v = 0; v < vogais.length; v++) {
				if (vogais[v] == index) {
					vogalSelecionada = v;
				}
			}
			if (vogalSelecionada >= 0) {
				if (pos == 2 && vogaisCont[vogalSelecionada] == 0) {
					result = index;
					Vogal vogal = new Vogal();
					vogal.setEntradaDados(entrada);
					vogal.setVogal("" + result);
					return vogal;
				} else if (result != null && result == index) {
					result = null;
				}
				vogaisCont[vogalSelecionada]++;
				pos = 1;
			} else {
				if (pos == 1) {
					pos++;
				} else {
					pos = 0;
				}
			}
		}
		return new Vogal();
	}

}
