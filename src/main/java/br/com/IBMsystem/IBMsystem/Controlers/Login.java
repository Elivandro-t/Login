package br.com.IBMsystem.IBMsystem.Controlers;

import br.com.IBMsystem.IBMsystem.DTOusuario.AlterPassword;
import br.com.IBMsystem.IBMsystem.DTOusuario.DetalheUsuario;
import br.com.IBMsystem.IBMsystem.DTOusuario.PerfilDto;
import br.com.IBMsystem.IBMsystem.DTOusuario.UserDTO;
import br.com.IBMsystem.IBMsystem.Exeption.ExeptionMsg;
import br.com.IBMsystem.IBMsystem.Exeption.Msg;
import br.com.IBMsystem.IBMsystem.Models.DTO.PerfilRepository;
import br.com.IBMsystem.IBMsystem.Models.Perfil;
import br.com.IBMsystem.IBMsystem.Models.Usuarios;
import br.com.IBMsystem.IBMsystem.Models.DTO.UsuarioRepository;
import br.com.IBMsystem.IBMsystem.Security.GerandoToken;
import br.com.IBMsystem.IBMsystem.validator.RegraDeNegocioValidator.AlterandoHora;
import br.com.IBMsystem.IBMsystem.validator.RegraDeNegocioValidator.ValidatorEmail;
import br.com.IBMsystem.IBMsystem.validator.RegraDeNegocioValidator.ValidatorPassword;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Login {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private GerandoToken gerandoToken;
    @Autowired
    private ValidatorPassword password;
    @Autowired
    private AlterandoHora alterandoHora;
    @Autowired
    private ValidatorEmail email;
    @Autowired
    private PerfilRepository perfilRepository;
    @PostMapping("/login")
    @Transactional
    public ResponseEntity Login(@RequestBody @Valid UserDTO user){
           if (user==null){
               throw new RuntimeException("campos não pode ser nulo");
           }
               var token = new UsernamePasswordAuthenticationToken(user.email(),user.password());
               var result = authenticationManager.authenticate(token);
               var auth = gerandoToken.geradorDeToken((Usuarios) result.getPrincipal(),result.getName());
               return ResponseEntity.ok(new DetalheUsuario(auth));
    }
    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid UserDTO userDTO){
        email.validator(userDTO.email());
        var user = usuarioRepository.pegandoUsuarioExistente(userDTO.email());
        if(user==null){
            throw new ExeptionMsg("email esta nulo");
        }
        if(user.isPresent()){
            throw new ExeptionMsg("usuario ja registrado");
        }
        password.validator(userDTO.password());
        var usuario = new Usuarios(userDTO);
        var registrador = usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(new Msg("registrado com sucesso "+" seja bem vindo "+registrador.getNome()));
    }
     @PutMapping("/alter")
     @Transactional
    public ResponseEntity alterPassWord(@RequestBody AlterPassword alterPassword){
       var usuario =  password.AlterarPassword(alterPassword);
         if(alterPassword==null){
             throw new ExeptionMsg("dados nao pode ser nulos");
         }
         password.validator(alterPassword.newPassword());
         usuario.alterar(alterPassword);
       return ResponseEntity.ok().body(new Msg("senha alterada com sucesso!"));
    }
    @PutMapping("/alter/esqueceu-a-senha")
    @Transactional
    public ResponseEntity Password(@RequestBody AlterPassword alterPassword){
        var usuario =  password.Alterar(alterPassword);
        if(alterPassword==null){
            throw new ExeptionMsg("dados nao pode ser nulos");
        }
        email.validator(alterPassword.email());
        password.validator(alterPassword.newPassword());
        usuario.alterar(alterPassword);
        return ResponseEntity.ok().body(new Msg("senha alterada com sucesso!"));
    }
    @PostMapping("/perfil")
    @Transactional
    public  ResponseEntity<PerfilDto> criarPerfil(@RequestBody @Valid PerfilDto perfilDto){
          var perfil = perfilRepository.findOneByName(perfilDto.name());
          if(perfil.isPresent()){
              throw new RuntimeException("perfil ja existente");
          }else if(perfil==null){
              throw new RuntimeException("nao existe perfil cadastrado");
          }
        Perfil perfil1 = new Perfil(perfilDto);
          perfilRepository.save(perfil1);
          return ResponseEntity.ok().body(new PerfilDto(perfil1));
    }
}
