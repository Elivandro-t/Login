package br.com.IBMsystem.IBMsystem.validator.RegraDeNegocioValidator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Component
public class AlterandoHora {

     //LocalDateTime hora = LocalDateTime.now();
    LocalDate hora = LocalDate.now();
    LocalDate horaform = dataHora();
   public LocalDate dataHora(){
       DateTimeFormatter famatov = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       String form = hora.format(famatov);
       LocalDate HoraFormatad = LocalDate.parse(form,famatov);
        return HoraFormatad;
   }

}
