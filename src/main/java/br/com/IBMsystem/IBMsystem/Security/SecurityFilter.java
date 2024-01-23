package br.com.IBMsystem.IBMsystem.Security;

import br.com.IBMsystem.IBMsystem.Models.DTO.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GerandoToken gerandoToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
          var token = pegarToken(request);
       // verificando se token e nulo
        if(token!=null){
            // vindo da class gerador de token indo para o metudo convertendo token
            var auth = gerandoToken.convertendoConde(token);
            // buscando no banco de dado os nome do usuario
            var result = usuarioRepository.findByEmail(auth);


                   var aut = new UsernamePasswordAuthenticationToken(result, null, result.getAuthorities());
                   SecurityContextHolder.getContext().setAuthentication(aut);

        }
        // fazendo liberacao de rotas e cabeçalho
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request,response);
        }
    }
   // metudo de verificao vindo do authorizado
    private String pegarToken(HttpServletRequest request) {
        var authoraze = request.getHeader("Authorization");
        if(authoraze!=null){
            return authoraze.replace("Bearer","").trim();
        }
        return null;
    }
}
