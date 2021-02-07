/*********************************************************************/
/**   ACH2002 - Introdução à Análise de Algoritmos                  **/
/**   EACH-USP - Segundo Semestre de 2020                           **/
/**                                                                 **/
/**   EP2                                                           **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**   Pedro Vinicius Fonseca                      11848264          **/
/*********************************************************************/

import java.util.LinkedList;

public interface ICaminho {
    public void escolherTrajetoria(LinkedList<Coordenada> caminhoFeito, LinkedList<Item> item, double tempo,int valorItens, int pesoTotal);
    public void imprimirCaminho();
}