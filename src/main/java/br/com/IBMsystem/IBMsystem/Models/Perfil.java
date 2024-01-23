package br.com.IBMsystem.IBMsystem.Models;

import br.com.IBMsystem.IBMsystem.DTOusuario.PerfilDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @NotBlank
    private String name ;
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;


    public Perfil(PerfilDto perfilDto) {
        this.name = perfilDto.name();
        this.usuario = perfilDto.usuario();
    }
}
