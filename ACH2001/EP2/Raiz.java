/*********************************************************************/
/**   ACH2001 - Introdução à Programação                            **/
/**   EACH-USP - Primeiro Semestre de 2020                          **/
/**   2020194 - Norton Trevisan Roman                               **/
/**                                                                 **/
/**   Segundo Exercício-Programa                                    **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**                                                                 **/
/**   Data de entrega: 05/05/2020    Data de Entrega Máxima:  06/05 **/
/*********************************************************************/

/*
	Cálculo para raiz quadrada
*/
public class Raiz {
	/*
		Calcula a raiz quadrada de um valor, com uma determinada
		precisão. Retorna esse valor, ou -1 quando:
		
		* a < 0
		* epsilon <= 0
		* epsilon >= 1
		
		Parâmetro:
			a - valor cuja raiz quadrada será calculada
			epsilon - precisão do cálculo
	*/
	static double raizQuadrada(double a, double epsilon) {
    // declarando uma variavel auxiliar que armazerá o valor de X antecessor.
    Double var_aux;
    // valor de inicial do X. Do enunciado temos que é a/2.
    Double x = a*0.5;
    // valor da diferença entre x e var_aux
    Double diferenca;

		if(a < 0 || epsilon <= 0 || epsilon >= 1){
      return -1;
    }

    if( a == 0 || a == 1){
      return a;
    }

    do {
      /**
       * Variavel auxiliar recebendo o valor do x antes do calculo. Portanto, 
       * pode ser considerar o valor antecessor a X.
       */
      var_aux = x;

      // equação de calulo calculo de aproximação de raiz quadrada
      x = 0.5*(var_aux+(a/var_aux));

      // armazenando a diferença entre os valores de x e var_aux
      diferenca = x - var_aux;

      // colocando o valor em módulo, caso seja necessário
      if(diferenca < 0) {
        diferenca = -diferenca; 
      }

    /**
     *  saíra do lopping quando a diferença entre o x e seu antecessor for menor
     *  do que 0.
     */ 
    } while(diferenca >= epsilon);

    return x;
	}

	/*
		Apenas para seus testes. ISSO SERÁ IGNORADO NA CORREÇÃO
	*/
	public static void main(String[] args) {
		double valor = 63;
		double precisao = 0.001;
    System.out.println("Raiz de : "+valor+" = "+raizQuadrada(valor, precisao));
    
    valor = 1; 
    System.out.println("Raiz de : "+valor+" = "+raizQuadrada(valor, precisao));
    
    valor = -100; 
    System.out.println("Raiz de : "+valor+" = "+raizQuadrada(valor, precisao));
    
    valor = 98789; 
		System.out.println("Raiz de : "+valor+" = "+raizQuadrada(valor, precisao));
	}
}