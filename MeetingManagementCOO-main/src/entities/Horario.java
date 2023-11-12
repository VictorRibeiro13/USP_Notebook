package entities;

import java.time.LocalDateTime;

public class Horario implements Comparable<Horario> {
    private String participante;
    private char situacao;
    private LocalDateTime hora;

    public Horario(String p, char s, LocalDateTime h) {
        this.participante = p;
        this.situacao = s;
        this.hora = h;
    }

    public char getSituacao(){
        return situacao;
    }
    
    public LocalDateTime getHora(){
        return hora;
    }
    
    public String getParticipante(){
        return participante;
    }
    
    @Override
    public int compareTo(Horario h) {
        if (this.hora.isBefore(h.hora)){
            return -1;
        }
        else if (this.hora.isAfter(h.hora)){
            return 1;
        }
        
        else if (this.hora.isEqual(h.hora) ){
            if (this.situacao  == 'i'&& h.situacao == 'f') {
                return 1;
            }
            else if (this.situacao == 'f' && h.situacao== 'i'){
                return -1;
            }
        }
        return 0;
    } 
}
