package br.com.IBMsystem.IBMsystem.Models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.lang.annotation.DeclareAnnotation;

import java.time.LocalDateTime;

public record UpdateChamado(
        @NotNull
        String id,
        @NotBlank(message = "compo nao pode ser vazio")
        String setor,
        @NotBlank(message = "compo nao pode ser vazio")
        String modelo,
        @NotBlank(message = "campo nao pode ser vazio")
        String solicitante,
        @NotBlank(message = "compo nao pode ser vazio")
        String patrimonio,
        @NotBlank(message = "compo nao pode ser vazio")
        String mac,
        @NotBlank(message = "compo nao pode ser vazio")
        String descricao,
        @NotBlank(message = "campo nao pode ser vazio")
        String tipoDeDefeito

) {
}
