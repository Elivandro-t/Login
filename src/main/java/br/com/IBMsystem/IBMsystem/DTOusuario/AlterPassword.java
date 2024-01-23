package br.com.IBMsystem.IBMsystem.DTOusuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterPassword(@NotBlank  String id,String email, String password, @NotBlank() @Size(min = 5,message = "senha nao pode ser menor que 5") String newPassword) {
}
