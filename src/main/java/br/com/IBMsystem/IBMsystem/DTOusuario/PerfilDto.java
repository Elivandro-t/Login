package br.com.IBMsystem.IBMsystem.DTOusuario;

import br.com.IBMsystem.IBMsystem.Models.Perfil;
import br.com.IBMsystem.IBMsystem.Models.Usuarios;

public record PerfilDto(long id, String name,Usuarios usuario) {
    public PerfilDto(Perfil perfil1) {
        this(perfil1.getId(),perfil1.getName(),perfil1.getUsuario());
    }
}
