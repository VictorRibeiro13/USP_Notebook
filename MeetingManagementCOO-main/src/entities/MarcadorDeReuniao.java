package entities;

import java.util.Collection;
import java.util.Collections;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MarcadorDeReuniao {
    private  List<Horario> horarios = new ArrayList<Horario>();
    private static int numPart;
    private static Scanner sc = new Scanner(System.in);
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal,
            Collection<String> listaDeParticipantes) {
                this.dataInicial = dataInicial;
                this.dataFinal = dataFinal;

                System.out.println("Qual é o número de participantes?");
                numPart = sc.nextInt();
                for (int i=1; i<=numPart; i++) {
                    System.out.printf("Participante %d: \n", i);
                    String participante = sc.next();
                    listaDeParticipantes.add(participante); 
                }
                
                for (String p : listaDeParticipantes) {
                    LocalDate dataAtual = dataInicial;
                    while(dataAtual.isBefore(dataFinal) || dataAtual.equals(dataFinal)){
                        System.out.println(p + ", quantos períodos de horários você tem disponíveis para o  dia "+ dataAtual.format(formatter) + "?");
                        int quantosHorarios = sc.nextInt();
                        LocalDateTime dataAtt = dataAtual.atStartOfDay();
                        LocalDateTime inicio = dataAtt;
                        LocalDateTime fim = null;
                        for(int i=1; i<=quantosHorarios; i++){
                            System.out.println("No seu " + i + "° horário do dia " + dataAtual.format(formatter) + ":");
                            indicaDisponibilidadeDe (p , inicio, fim);    
                        }
                        dataAtual = dataAtual.plusDays(1);
                    }
                }
                
                mostraSobreposicao();     
    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        LocalDateTime reserva = inicio;
        
        String reservas = reserva.format(formatter);
        
        System.out.println("[Formato: 'hh:mm'] Qual o horário INICIAL?");
        String horarioInicial = sc.next();
        String horarioInicio = reservas+ " " +horarioInicial;
        LocalDateTime concatInicio = LocalDateTime.parse(horarioInicio, formatterDateTime);
        
        System.out.println("[Formato: 'hh:mm'] Qual o horário FINAL?");
        String horarioFinal = sc.next();
        String horarioFim = reservas + " " + horarioFinal;
        LocalDateTime concatFim = LocalDateTime.parse(horarioFim, formatterDateTime);
        
        Horario horarioInicialFormatado = new Horario(participante, 'i', concatInicio);
        Horario horarioFinalFormatado = new Horario(participante, 'f', concatFim);      
        
        horarios.add(horarioInicialFormatado);
        horarios.add(horarioFinalFormatado);
    }

    public void mostraSobreposicao() {
        LocalDate dia = dataInicial;
        String[] stepData = new String[3];
        System.out.println("Este é o relatório de horários dos participantes:");

        while(dia.isBefore(dataFinal) || dia.equals(dataFinal)){
            System.out.println(" ---------------------- \n");
            System.out.println("|  No dia: " +  dia.format(formatter) + "  |");
            System.out.println("\n ---------------------- ");
            relatorioDeHorariosPorDia(dia);

            dia = dia.plusDays(1);
        }
        
        Collections.sort(horarios);
        int quantidadeInicios = 0;
        List<String[]> intervalos = new ArrayList<>();
        
        boolean achei= false;
        for (Horario horario : horarios) {            
            if(horario.getSituacao() ==  'i') {
                quantidadeInicios++;
            }
            else {
                quantidadeInicios--;
            } 
            
            if (achei && horario.getSituacao() == 'f'){
                String min=  (horario.getHora().getMinute() < 10) ? "0"+horario.getHora().getMinute(): horario.getHora().getMinute()+"";
                stepData[2] = horario.getHora().getHour()+":"+min;
                achei = false;
                intervalos.add(stepData);
                stepData = new String[3];
            }
            
            if (quantidadeInicios == numPart){
                stepData[0] = horario.getHora().getDayOfMonth() + "/" + horario.getHora().getMonthValue() + "/"  + horario.getHora().getYear();
                String min =  (horario.getHora().getMinute() < 10) ? "0"+horario.getHora().getMinute(): horario.getHora().getMinute()+"";
                stepData[1] = horario.getHora().getHour()+":"+ min;
                achei = true;
            }
                    
        }
        System.out.println(" ");
        System.out.println("-----------------------------------------------------------");

        if(intervalos.isEmpty()){
            System.out.println("Não é possível marcar reunião com todos os participantes entre o dia " + dataInicial.format(formatter) + " e " + dataFinal.format(formatter));
        }
        else{
            System.out.println("O(s) horário(s) em que todos os participantes estão disponíveis são:");
        }
        for (String[] intervalo : intervalos) {
            
            System.out.println("");
            System.out.println(" ------------------------------------------------------------- ");
            System.out.println("| Dia: " + intervalo[0]+ " | Horário Início: "+ intervalo[1] + " | Horário Final: "+ intervalo[2] + " |");
            System.out.println(" ------------------------------------------------------------- ");

        }
    }

    public void relatorioDeHorariosPorDia(LocalDate dia){
        String ParticipanteDoMomento = null;
        for (Horario horario : horarios ){
            if(dia.format(formatter).equals(horario.getHora().toLocalDate().format(formatter))){
                if(horario.getParticipante().equals(ParticipanteDoMomento)){

                    if (horario.getSituacao() == 'i'){
                        System.out.println("Horário Inicial");
                    }
                    else{
                        System.out.println("Horário Final");
                    }
                    String min=  (horario.getHora().getMinute() < 10) ? "0"+horario.getHora().getMinute(): horario.getHora().getMinute()+"";
                    System.out.println("Horário: " + horario.getHora().getHour() + ":" + min);
                    return;
                } 
                ParticipanteDoMomento = horario.getParticipante();
                System.out.println("Participante: " + horario.getParticipante());
                if (horario.getSituacao() == 'i'){
                    System.out.println("Horário Inicial");
                }
                else{
                    System.out.println("Horário Final");
                }
                String min = (horario.getHora().getMinute() < 10) ? "0"+horario.getHora().getMinute(): horario.getHora().getMinute()+"";
                System.out.println("Horário: " + horario.getHora().getHour() + ":" + min);
            }
        }  
    }
}
