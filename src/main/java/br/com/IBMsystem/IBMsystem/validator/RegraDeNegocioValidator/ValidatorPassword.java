package br.com.IBMsystem.IBMsystem.validator.RegraDeNegocioValidator;

import br.com.IBMsystem.IBMsystem.DTOusuario.AlterPassword;
import br.com.IBMsystem.IBMsystem.DTOusuario.NewPassword;
import br.com.IBMsystem.IBMsystem.Exeption.ExeptionMsg;
import br.com.IBMsystem.IBMsystem.Models.DTO.UsuarioRepository;
import br.com.IBMsystem.IBMsystem.Models.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class ValidatorPassword {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public void validator(String password){
        if(insegurePassowd(password)){
            throw new ExeptionMsg("senha nao e recomendavel");
        }
    }
    private static boolean insegurePassowd(String str){
        return str.length()<8||retornaNuber(str);
    }
    private static boolean retornaNuber(String str){
        return str.matches("\\d+");
    }
    public Usuarios  AlterarPassword(AlterPassword alterPassword) {
        var usuario = usuarioRepository.getReferenceById(alterPassword.id());
        if(!passwordEncoder.matches(alterPassword.password(),usuario.getPassword())){
            throw  new ExeptionMsg("senha invalida");
        } if(!passwordEncoder.matches(alterPassword.newPassword(),usuario.getPassword())){
            return usuario;
        }
        throw  new ExeptionMsg("senha nao podem ser iguais");
    }
    public Usuarios  Alterar(AlterPassword alterPassword) {
        var usuario = usuarioRepository.findByEmail(alterPassword.email());
        if(usuario==null){
            throw new ExeptionMsg("usuario invalido");
        }
        if(!passwordEncoder.matches(alterPassword.password(),usuario.getPassword())){
            throw  new ExeptionMsg("senha invalida");
        } if(!passwordEncoder.matches(alterPassword.newPassword(),usuario.getPassword())){
            return usuario;
        }
        throw  new ExeptionMsg("senha nao podem ser iguais");
    }
}
