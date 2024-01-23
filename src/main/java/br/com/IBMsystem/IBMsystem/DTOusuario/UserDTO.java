package br.com.IBMsystem.IBMsystem.DTOusuario;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDTO(String name, String email, String password, List<PerfilDto> perfil) {
}
