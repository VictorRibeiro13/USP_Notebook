package utils;
import java.time.LocalDate;


public class VerificadorData {
    public static boolean dataInvalida (LocalDate Inicio, LocalDate Fim){
        return Fim.isBefore(Inicio);  
    } 
}
