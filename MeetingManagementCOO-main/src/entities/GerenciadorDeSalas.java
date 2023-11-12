package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import utils.*;

public class GerenciadorDeSalas {
    private List<Sala> salas = new ArrayList<Sala>();
    private List<Sala> listaSalas = new LinkedList<>();
    private List<Reserva> listaReservas = new LinkedList<>();
    private Collection<Reserva> colecaoReserva = new ArrayList<Reserva>();
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) throws SalaExistenteException{
        for(Sala nsala : salas){
            if(nsala.getNome().equals(nome)){
                throw new SalaExistenteException("Sala '" + nsala.getNome() + "' já existente");
            }
        }
        Sala sala = new Sala(nome, capacidadeMaxima, descricao);
        salas.add(sala);
    }
    
    public void removeSalaChamada(String nomeDaSala){
        for(Sala sala : salas){
            if(nomeDaSala.equals(sala.getNome())){
                salas.remove(sala);
                return;
            }
        }
        System.out.println("Sala não encontrada");
    }
    
    public List<Sala> listaDeSalas(){
        return this.salas;
    }
    
    
    public void adicionaSala(Sala novaSala){
        salas.add(novaSala);
    }
    
    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) throws InterseccaoReservaException, SalaNaoExistenteException {
        Sala salaReserva = null;
        Reserva reserva = null;
        for(Sala sala : salas){
            if(nomeDaSala.equals(sala.getNome())){
                salaReserva = sala;
                break;
            }
        }
        
        if(salaReserva == null){
            throw new SalaNaoExistenteException("Sala não existe! Crie ou então insira o nome correto");
        }
        for(Reserva iReserva : colecaoReserva){
            if(iReserva.getSala() == salaReserva){
                if(iReserva.getInicio().isBefore(dataFinal) && iReserva.getFim().isAfter(dataInicial)){
                    throw new InterseccaoReservaException("Sala já esta reservada de " + iReserva.getInicio().format(formatter) + " até " + iReserva.getFim().format(formatter));
                }
            }
        }
        
        reserva = new Reserva(salaReserva, dataInicial, dataFinal);
        colecaoReserva.add(reserva);
        
        return reserva;
    }
    
    public void cancelaReserva(Reserva cancelada){
        colecaoReserva.remove(cancelada);
    }
    
    public Collection<Reserva> reservasParaSala(String nomeSala){
        Collection<Reserva> reservasParaSala = new LinkedList<>();

        for(int i = 0; i < listaReservas.size(); i++){
            if(listaReservas.get(i).getNomeSala() == nomeSala) reservasParaSala.add(listaReservas.get(i));
        }
        
        return reservasParaSala;
    }
    
    public void imprimeReservasDaSala(String nomeSala) {
       int cont = 0;
       System.out.println("Sala: " + nomeSala);
    //    System.out.println("Descrição da sala: " + nomeSala.observacoes);
       for(Reserva iReserva : colecaoReserva){
           if(iReserva.getNomeSala() == nomeSala){
               System.out.println("Inicio: " + iReserva.getInicio().format(formatter));
               System.out.println("Fim: " + iReserva.getFim().format(formatter));
               System.out.println();
               cont++;
           }
       }
       
       if(cont == 0){
           System.out.println("Não existe reservas para essa sala!");
       }
    }
}


