/*********************************************************************/
/**   ACH2002 - Introdução à Análise de Algoritmos                  **/
/**   EACH-USP - Segundo Semestre de 2020                           **/
/**                                                                 **/
/**   EP2                                                           **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**   Pedro Vinicius Fonseca                      11848264          **/
/*********************************************************************/

public class Coordenada {
    private boolean estaBloqueada, estaVisitada;
    private int linha, coluna;
    private double tempoFinal;
    private Item item;

    public Coordenada(char c, int lin, int col) {
        this.linha = lin;
        this.coluna = col;
        this.estaBloqueada = c == 'X' ? true : false;
        this.estaVisitada = false;
        this.item = null;
        this.tempoFinal = 0;
    }

    public boolean EstaBloqueada() { return estaBloqueada; }

    public void setEstaBloqueada(boolean estaBloqueada) { this.estaBloqueada = estaBloqueada; }

    public boolean EstaVisitada() { return estaVisitada; }

    public void setEstaVisitada(boolean estaVisitada) { this.estaVisitada = estaVisitada; }

    public int getLinha() { return linha; }

    public void setLinha(int linha) { this.linha = linha; }

    public int getColuna() { return coluna; }

    public void setColuna(int coluna) { this.coluna = coluna; }

    public double getTempoFinal() { return tempoFinal; }

    public void setTempoFinal(double tempoFinal) { this.tempoFinal = tempoFinal; }

    public void setItem(int valor, int peso) {
        this.item = new Item(this.linha, this.coluna, valor, peso);
    }

    public Item getItem() { return (item != null) ? this.item : null; }

    public boolean verficaItem() { return item != null; }

    public void printCoordenada() {
        System.out.println(getLinha()+" "+getColuna());
    }

}
