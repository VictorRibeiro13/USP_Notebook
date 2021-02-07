/*********************************************************************/
/**   ACH2002 - Introdução à Análise de Algoritmos                  **/
/**   EACH-USP - Segundo Semestre de 2020                           **/
/**                                                                 **/
/**   EP2                                                           **/
/**                                                                 **/
/**   Victor dos Santos Ribeiro                   11917559          **/
/**   Pedro Vinicius Fonseca                      11848264          **/
/*********************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Map {
    private Coordenada[][] mapa;
    private Coordenada inicioCoordenada;
    private Coordenada fimCoordenada;

    public Map(String fileName) {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(fileName));
            String[] primeiraLinha = leitor.readLine().split(" ");

            int lin = Integer.parseInt(primeiraLinha[0]);
            int col = Integer.parseInt(primeiraLinha[1]);

            this.mapa = new Coordenada[lin][col];

            for(int i = 0; i < lin; i++) {
                char[] colunas = leitor.readLine().toCharArray();
                for(int j = 0; j < col; j++) this.mapa[i][j] = new Coordenada(colunas[j], i, j);
            }

			String[] item;
            int numItens = Integer.parseInt(leitor.readLine());
            int linhaItem,colunaItem, valorItem, pesoItem ;

            for(int i = 0; i < numItens; i++) {
                item = leitor.readLine().split(" ");

                linhaItem = Integer.parseInt(item[0]);
                colunaItem = Integer.parseInt(item[1]);
                valorItem = Integer.parseInt(item[2]);
                pesoItem = Integer.parseInt(item[3]);

                Coordenada coordenada = this.mapa[linhaItem][colunaItem];
                coordenada.setItem(valorItem, pesoItem);
            }

            String[] inicioCordenadas = leitor.readLine().split(" ");
            String[] fimCordenada = leitor.readLine().split(" ");

            int inicioCordenadaLinha = Integer.parseInt(inicioCordenadas[0]);
            int inicioCordenadaColuna = Integer.parseInt(inicioCordenadas[1]);

            int fimCordenadaLinha = Integer.parseInt(fimCordenada[0]);
            int fimCordenadaColuna = Integer.parseInt(fimCordenada[1]);

            this.inicioCoordenada = this.mapa[inicioCordenadaLinha][inicioCordenadaColuna];
            this.fimCoordenada = this.mapa[fimCordenadaLinha][fimCordenadaColuna];
        } catch(IOException e) {
            System.out.println("Erro ao carragar mapa !");
            e.printStackTrace();
        }
    }

    public Coordenada getCoordenada(int linha, int coluna) {
        return mapa[linha][coluna];
    }

    public int getMaximoLinhas(){
        return this.mapa.length-1;
    }

    public int getMaximoColunas(){
        return this.mapa[0].length-1;
    }

    public Coordenada getInicioCoordenada() {
        return inicioCoordenada;
    }

    public void setInicioCoordenada(Coordenada inicioCoordenada) {
        this.inicioCoordenada = inicioCoordenada;
    }

    public Coordenada getFimCoordenada() {
        return fimCoordenada;
    }

    public void setFimCoordenada(Coordenada fimCoordenada) {
        this.fimCoordenada = fimCoordenada;
    }
}
