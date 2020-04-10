/*********************************************************************/
/**   ACH2001 - Introdução à Programação                            **/
/**   EACH-USP - Primeiro Semestre de 2020                          **/
/**   2020194 - Norton Trevisan Roman                               **/
/**                                                                 **/
/**   Primeiro Exercício-Programa                                   **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**                                                                 **/
/**   15/04/2020                                                    **/
/*********************************************************************/

/*
	Caixa eletrônico das Ilhas Weblands, com estoque ilimitado de cédulas
	de B$50,00, B$10,00, B$5,00 e B$1,00.
*/
public class CaixaEletronico {
    // Número de cédulas de B$50,00
    static int n50;

    // Número de cédulas de B$10,00
    static int n10;

    // Número de cédulas de B$5,00
    static int n5;

    // Número de cédulas de B$1,00
    static int n1;

    /*
        Determina a quantidade de cada nota necessária para totalizar
        um determinado valor de retirada, de modo a minimizar a quantidade
        de cédulas entregues.

        Abastece as variáveis globais n50,n10, n5 e n1 com seu respectivo
        número de cédulas.

        Parâmetro:
            valor - O valor a ser retirado
    */
    static void fazRetirada(int valor) {
      // Determinando se a ser retirado é negativo
        if (valor < 0) {
          // atribuindo a todas as variaveis o valor -1, conforme o enunciado
          n50 = n10 = n5 = n1 = -1;
          return;
        }

        // Determinando se o valor é multiplo de 50
        if(valor%50 == 0) {

          // Calculando quantas notas de 50 o Caixa dará
          n50 = valor/50;
          // Retorna a função para o método Main
          return;

        }else{
          // Calculando quantas notas de 50 o Caixa dará
          n50 = valor/50;
          //Armazenando o valor restante que não é multiplo de 50
          valor = valor%50;
        }


        // Determinando se o valor é multiplo de 10
        if(valor%10 == 0) {
          // Calculando quantas notas de 10 o Caixa dará
          n10 = valor/10;
          // Retorna a função para o método Main
          return;

        }else{
          // Calculando quantas notas de 10 o Caixa dará
          n10 = valor/10;
          //Armazenando o valor restante que não é multiplo de 10
          valor = valor%10;
        }
        
        // Determinando se o valor é multiplo de 5
        if(valor%5 == 0){
          // Calculando quantas notas de 5 o Caixa dará
          n5 = valor/5;
          // Retorna a função para o método Main
          return;

        }else {
          // Calculando quantas notas de 5 o Caixa dará
          n5 = valor/5;
          //Armazenando o valor restante que não é multiplo de 5
          valor = valor%5;
        }
        /** 
         *  Como o resto será multiplo de 1, basta adicionar a n1, ou seja,
         *  o restante das notas "sairão" como notas de 1 real.
         * */ 
        n1 = valor;
    }

    /*
        Apenas para seus testes. ISSO SERÁ IGNORADO NA CORREÇÃO
    */
    public static void main(String[] args) {

        // Exemplos de teste:
        System.out.println("0");
        fazRetirada(0);
        System.out.println("Notas de 50: "+n50);
        System.out.println("Notas de 10: "+n10);
        System.out.println("Notas de 5:  "+n5);
        System.out.println("Notas de 1:  "+n1);
        System.out.println("================");


        System.out.println("-500:");
        fazRetirada(-500);
        System.out.println("Notas de 50: "+n50);
        System.out.println("Notas de 10: "+n10);
        System.out.println("Notas de 5:  "+n5);
        System.out.println("Notas de 1:  "+n1);
        System.out.println("================");
    }
}