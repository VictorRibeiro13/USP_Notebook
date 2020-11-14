/*********************************************************************/
/**   ACH2001 - Introdução à Programação                            **/
/**   EACH-USP - Primeiro Semestre de 2020                          **/
/**   2020194 - Norton Trevisan Roman                               **/
/**                                                                 **/
/**   Segundo Exercício-Programa                                    **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**                                                                 **/
/**   Data de entrega: 25/05/2020    Data de Entrega Máxima:  01/06 **/
/*********************************************************************/

/*
	Jogo da Velha - programa para verificar o status de um jogo.
	
	Lista de Status calculado:
	0 - Jogo nao iniciado: o tabuleiro esta 'vazio', isto é sem pecas X e O;
    1 - Jogo encerrado1: o primeiro jogador (que usa as pecas X) é o ganhador;
    2 - Jogo encerrado2: o segundo jogador (que usa as pecas O) é o ganhador;
    3 - Jogo encerrado3: empate - todas as casas do tabuleiro estao preenchidas com X e O, mas nenhum dos jogadores ganhou;
    4 - Jogo ja iniciado e em andamento: nenhuma das alternativas anteriores.	
*/

public class JogoDaVelha {
	static final char pecaX = 'X';
	static final char pecaY = 'O';
  static final char espacoVazio = ' ';
   
	/*
		Determina o status de uma partida de Jogo da Valha
		
		Entrada:
			tabuleiro - matriz 3x3 de caracteres representando uma partida valida de Jogo da Velha
			
		Saida:
			um inteiro contendo o status da partida (valores v�lidos de zero a quatro)
	*/
	static int verificaStatus(char[][] tabuleiro) {
    int status = -1;
    // variavel responsavel por contar espaços vazios
    int countVazio = 0;

    // verificando se as duas diagonais possuem 3 valores consecutivos iguais
    if((tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != ' ') || 
      (tabuleiro[2][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[0][2] && tabuleiro[2][0] != ' ')) {
			status = tabuleiro[0][0] == 'X' ?  1 : 2;
    } 
    
    // laço de repetição para para poder verificar os valores do array
    for(int i=0; i < tabuleiro.length; i++) {
      
      // verificando se as linhas do tabuleiros possuem valores 3 valores consecutivos iguais
      if(tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][2] != ' ') {
        status = tabuleiro[i][0] == 'X' ? 1 : 2;
      }

      // verificando se as colunas do tabuleiros possuem valores 3 valores consecutivos iguais
      if(tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[2][i] != ' ') {
        status = tabuleiro[0][i] == 'X' ? 1 : 2;
      }
      
      // contando a quantidade de casas vazias
      for (int j=0; j<tabuleiro[i].length ; j++) {
        if(tabuleiro[i][j] == ' ') {
					countVazio++;
        }
      }
    }

    // verificando se todas as casas estão vazias
    if(countVazio == 9) {
      status = 0;
    }

    // verificando se deu empate
    if(countVazio == 0 && status == -1) {
      status = 3;
    }

    // verificando se o jogo está em andamento
    if(countVazio > 0 && status == -1 ) {
      status = 4;
    }	

		return status;
	}
	
	public static void main(String[] args) {
		char[][] tab0 = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
		char[][] tab1 = {{'X','X','X'},{'O','O',' '},{' ',' ',' '}};
		char[][] tab2 = {{'O','X','X'},{'X','O','O'},{' ',' ','O'}};
		char[][] tab3 = {{'O','X','X'},{'X','O','O'},{'O','X','X'}};
    char[][] tab4 = {{' ',' ',' '},{'X','O','X'},{' ',' ',' '}};

		System.out.println("Status calculado: " + verificaStatus(tab0));
		System.out.println("Status esperado para o tabuleiro0: 0\n");

		System.out.println("Status calculado: " + verificaStatus(tab1));
		System.out.println("Status esperado para o tabuleiro1: 1\n");

		System.out.println("Status calculado: " + verificaStatus(tab2));
		System.out.println("Status esperado para o tabuleiro2: 2\n");
		
    System.out.println("Status calculado: " + verificaStatus(tab3));
    System.out.println("Status esperado para o tabuleiro3: 3\n");

    System.out.println("Status calculado: " + verificaStatus(tab4));
    System.out.println("Status esperado para o tabuleiro4: 4\n");
	}
}