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

public class Explorador {
    private LinkedList<Item> itens;
    private LinkedList<Coordenada> posicoesVisitadas;
    private ICaminho caminho;
    private Map mapa;

    private double tempoPassado;
    private int pesoItens;
    private int valorItem;

    private final int valorInicial = 0;

    public Explorador(String mapa, ICaminho caminho) {
        this.itens = new LinkedList<Item>();
        this.posicoesVisitadas = new LinkedList<Coordenada>();
        this.caminho = caminho;
        this.mapa = new Map(mapa);

        this.tempoPassado = valorInicial;
        this.pesoItens = valorInicial;
        this.valorItem = valorInicial;

        Coordenada coordenada = this.mapa.getInicioCoordenada();

        if (coordenada.equals(null)) {
            System.out.println("Coordenada Invalida !");
            System.exit(0);
        }
        proximaCoordenada(coordenada.getLinha(), coordenada.getColuna());
    }

    public double tempoParaChegar() {
        final double contante = 10;
        double num = 1.0 +  (pesoItens / contante);

        double intervalo = Math.pow(num, 2);

        return intervalo;
    }

    public int getPesoTesouros() {
        return this.pesoItens;
    }

    public double getTempoPassado() {
        return this.tempoPassado;
    }

    public int getValorItens() {
        return this.valorItem;
    }

    public void sairDaCoordenada(Coordenada coordenada) {
        if(coordenada.verficaItem()) {
            Item item = coordenada.getItem();
            this.valorItem -= item.getValue();
            this.pesoItens -= item.getWeight();
            this.itens.remove(item);
        }
        coordenada.setEstaVisitada(false);
        this.tempoPassado -= coordenada.getTempoFinal();
        this.posicoesVisitadas.remove(coordenada);
    }

    public void proximaCoordenada(int lin, int col) {
        if(lin > this.mapa.getMaximoLinhas() || col > this.mapa.getMaximoColunas() || col < 0  || lin < 0) {
            return;
        }

        Coordenada coordenada = mapa.getCoordenada(lin, col);
        if (coordenada.EstaBloqueada() || coordenada.EstaVisitada()) return;

        if (coordenada != this.mapa.getInicioCoordenada()) {
            coordenada.setTempoFinal(this.tempoParaChegar());
            this.tempoPassado += tempoParaChegar();
        }

        posicoesVisitadas.add(coordenada);
        coordenada.setEstaVisitada(true);

        if(coordenada.verficaItem()) {
            Item item = coordenada.getItem();
            this.pesoItens += item.getWeight();
            this.valorItem += item.getValue();
            this.itens.add(item);
        }

        if(this.mapa.getFimCoordenada() == coordenada) {
            caminho.escolherTrajetoria(posicoesVisitadas, itens, getTempoPassado(), getValorItens(), getPesoTesouros());
        } else {
            proximaCoordenada(lin-1, col);
            proximaCoordenada(lin, col+1);
            proximaCoordenada(lin+1, col);
            proximaCoordenada(lin, col-1);
        }
        sairDaCoordenada(coordenada);
    }
}

