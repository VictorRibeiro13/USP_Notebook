/*********************************************************************/
/**   ACH2002 - Introdução à Análise de Algoritmos                  **/
/**   EACH-USP - Segundo Semestre de 2020                           **/
/**                                                                 **/
/**   EP2                                                           **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**   Pedro Vinicius Fonseca                      11848264          **/
/*********************************************************************/

import javax.management.RuntimeErrorException;
import java.io.*;

public class EP2 {
	public static void main(String [] args) throws IOException {
		if (args.length < 2) throw new Error("Faltando argumentos na chamada a main");

		ICaminho caminho = CalcularCaminho(args[1]);
		new Explorador(args[0], caminho);
		caminho.imprimirCaminho();
	}


	public static ICaminho CalcularCaminho (String opcao) {
		final int caminho = Integer.parseInt(opcao);

		ICaminho caminhoObj;

		switch (caminho) {
			case 1: caminhoObj = new CaminhoMaisCurto(); break;
			case 2: caminhoObj = new CaminhoMaisLongo(); break;
			case 3: caminhoObj = new CaminhoMaisValioso(); break;
			case 4: caminhoObj = new CaminhoMaisRapido(); break;
			default: throw new RuntimeErrorException(null, "Opcao de caminho errada");
		}

		return caminhoObj;
	}
}
