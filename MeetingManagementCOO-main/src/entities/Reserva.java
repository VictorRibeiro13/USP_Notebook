package entities;

import java.time.LocalDateTime;

import entities.Sala;

public class Reserva {
    private Sala sala;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    
    public Reserva(Sala sala, LocalDateTime inicio, LocalDateTime fim){
        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public String getNomeSala(){
        return sala.getNome();
    }

    public Sala getSala(){
        return sala;
    }
    
    public LocalDateTime getInicio(){
        return inicio;
    }
    
    public LocalDateTime getFim(){
        return fim;
    }
}
