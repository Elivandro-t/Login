package br.com.IBMsystem.IBMsystem.validator.RegraDeNegocioValidator;

import br.com.IBMsystem.IBMsystem.Exeption.ExeptionMsg;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class ValidatorEmail {


    public void validator(String email){
        if(!isEmail(email)){
            throw new ExeptionMsg("email e invalido");
        }

    }

    private static boolean isEmail(String email){
        String regex =  "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern patten = Pattern.compile(regex);
        Matcher matcher = patten.matcher(email);
        return  matcher.matches();
    }
}
