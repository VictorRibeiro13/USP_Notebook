/*********************************************************************/
/**   ACH2002 - Introdução à Análise de Algoritmos                  **/
/**   EACH-USP - Segundo Semestre de 2020                           **/
/**                                                                 **/
/**   EP2                                                           **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**   Pedro Vinicius Fonseca                      11848264          **/
/*********************************************************************/

import java.text.DecimalFormat;
import java.util.LinkedList;

public class CaminhoMaisCurto implements ICaminho{
    LinkedList<Coordenada> caminhoFeito;
    LinkedList<Item> item;
    double tempo;
    int valorItens;
    int pesoTotal;

    @Override
    public void escolherTrajetoria(LinkedList<Coordenada> caminhoFeito, LinkedList<Item> item, double tempo,int valorItens, int pesoTotal) {
        if(this.caminhoFeito == null || caminhoFeito.size() < this.caminhoFeito.size()) {
            this.caminhoFeito = new LinkedList<Coordenada>(caminhoFeito);
            this.item = new LinkedList<Item>(item);
            this.tempo = tempo;
            this.valorItens = valorItens;
            this.pesoTotal = pesoTotal;
        }
    }

    @Override
    public void imprimirCaminho() {
        DecimalFormat formatadorTempo = new DecimalFormat("0.##");

        final Integer tamanhoCaminho = this.caminhoFeito.size();
        final String tempoFormatado = formatadorTempo.format(tempo).replace(",", ".");
        final Integer tamanhoItem = this.item.size();
        final Integer totalValorItens = this.valorItens;
        final Integer pesoTotal = this.pesoTotal;

        System.out.println(tamanhoCaminho.toString().concat(" ").concat(tempoFormatado));

        for (Coordenada coordenada : caminhoFeito) coordenada.printCoordenada();

        System.out.println(tamanhoItem.toString().concat(" ").concat(Integer.toString(totalValorItens)).concat(" ").concat(Integer.toString(pesoTotal)));

        for (Item i : item) i.printItem();
    }
}
