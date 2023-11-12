import entities.*;
import utils.VerificadorData;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.List;

public class Index {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            LocalDate dataInicial;
            LocalDate dataFinal;
            ArrayList<String> listaDeParticipantes = new ArrayList<String>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dataInicial = null; 
            dataFinal = null;
            boolean FimAntesDoInicio = true;
            while(FimAntesDoInicio == true){
            
                System.out.println("[FORMATO: 'dd/mm/aaaa'] Data Inicial:");
                String DataI = sc.next();
                dataInicial = LocalDate.parse(DataI, formatter);
                
                System.out.println("[FORMATO: 'dd/mm/aaaa'] Data Final:");
                String DataF = sc.next();
                dataFinal = LocalDate.parse(DataF, formatter);
                FimAntesDoInicio = VerificadorData.dataInvalida(dataInicial, dataFinal);
                if(FimAntesDoInicio){
                    System.out.println("A DATA FINAL é anterior a DATA INICIAL, por favor, reajuste as datas.");
                }
            }
            
            MarcadorDeReuniao marcar = new MarcadorDeReuniao();
            marcar.marcarReuniaoEntre (dataInicial, dataFinal, listaDeParticipantes);

            GerenciadorDeSalas sala = new GerenciadorDeSalas();

            System.out.println("Com base nisso deseja reservar algmumas salas? [S/n]");
            char temReserva = sc.next().charAt(0);
            if(temReserva == 'S') {
                System.out.println("Quantas salas deseja adicionar?");
                int quantidadeDeSalas = sc.nextInt();
                for(int i=1; i<=quantidadeDeSalas; i++){
                    System.out.println("Qual é o nome " + i + "º sala?");
                    String nomeDaSala = sc.next();
                    System.out.println("Qual a capacidade maxima?");
                    int capacidadeDaSala = sc.nextInt();
                    System.out.println("Qual é a descrição dessa sala?");
                    String descDaSala = sc.next();
                    sala.adicionaSalaChamada(nomeDaSala, capacidadeDaSala, descDaSala);
                }
                System.out.println("Quantas reservas deseja fazer?");
                int quantidadeDeReservas = sc.nextInt();
                for(int i=1; i<=quantidadeDeReservas; i++){
                    System.out.println("Qual é a sala da " + i + "º reserva?");
                    String nomeDaSala = sc.next();
                    System.out.println("[Formato: 'dd/mm/aaaa' 'hh:mm'] Qual a data inicial?");
                    String DataI = sc.next();
                    String HoraI = sc.next();
                    LocalDateTime DataInicial = LocalDateTime.parse(DataI + " " + HoraI, formatterDateTime);
                    System.out.println("[Formato: 'dd/mm/aaaa' 'hh:mm'] Qual a data final?");
                    String DataF = sc.next();
                    String HoraF = sc.next();
                    LocalDateTime DataFinal = LocalDateTime.parse(DataF + " " + HoraF, formatterDateTime);
                    sala.reservaSalaChamada(nomeDaSala, DataInicial, DataFinal);
                    System.out.println("Reserva da sala " + nomeDaSala + " realizada");
                }
            }
            sc.close();

        } catch (Exception err) {
            System.out.println("An error has occurred: " + err.getMessage());
        }
    }
}