package br.com.IBMsystem.IBMsystem.Exeption;

import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class TratandoErro {
    //tratando erro de pagina nao encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratandoError404(){
        return ResponseEntity.notFound().build();

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratandoeErro400(MethodArgumentNotValidException e){
        var error = e.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(tratandoErroEx::new).toList());


    }
    @ExceptionHandler(ExeptionMsg.class)
    public ResponseEntity validadoSeExisteUsuario(ExeptionMsg msg){
        var ms = msg.getMessage();
        return ResponseEntity.badRequest().body(new Msg(ms));


    }

    public  record tratandoErroEx(String campo,String msg){
        public tratandoErroEx(FieldError e){
            this(e.getField(),e.getDefaultMessage());
        }

    }

    @ExceptionHandler(RuntimeException.class)
    public  ResponseEntity runtime(RuntimeException e){
        return  ResponseEntity.badRequest().body(new Msg(e.getMessage()));
    }




}