package br.com.IBMsystem.IBMsystem.Security;

import br.com.IBMsystem.IBMsystem.Models.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Service
public class GerandoToken {
// gerando token e liberando para o usuario logado
    public String geradorDeToken(Usuarios usuarios, String name){
        try {
            var algorithm = Algorithm.HMAC256("123456");
         return JWT.create()
                 .withSubject(usuarios.getEmail())
                 .withClaim("id",usuarios.getId())
                 .withClaim("perfil",usuarios.getPerfil())
                 .withClaim("name",usuarios.getNome())
                 .withExpiresAt(DataExpiracao())
                    .withIssuer("17100150")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("erro ao gerar token"+exception);
        }
    }
    // recebendo o token e validando se e um usuario valido e retornando nome do usuario para consulta no filter
    public String convertendoConde(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.require(algorithm)
                    .withIssuer("17100150")
                    .build()
                    .verify(token).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException(exception);
            // Invalid signature/claims
        }
    }

    private Instant DataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
