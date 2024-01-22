package br.com.IBMsystem.IBMsystem.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TratrandoErro extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        var msg = "email e senha invalidos";
        return new ResponseEntity<>(new Msg(msg), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public  ResponseEntity use(InternalAuthenticationServiceException e){

        return ResponseEntity.badRequest().body(new Msg(e.getMessage()));
    }

}
