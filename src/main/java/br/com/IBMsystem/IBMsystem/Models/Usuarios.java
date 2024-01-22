package br.com.IBMsystem.IBMsystem.Models;

import br.com.IBMsystem.IBMsystem.DTOusuario.AlterPassword;
import br.com.IBMsystem.IBMsystem.DTOusuario.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "usuarios")
public class Usuarios implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String passwod;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "usuario")
    private List<Perfil> perfil = new ArrayList<>();

    public Usuarios(UserDTO user) {
        this.nome =user.name();
        this.email = user.email();
        this.passwod = new BCryptPasswordEncoder().encode(user.password());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.passwod;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void alterar(AlterPassword alterPassword) {
        this.passwod = new BCryptPasswordEncoder().encode(alterPassword.newPassword());
    }
    public void addPerfil(Perfil perfil) {
        this.perfil.add(perfil);
        perfil.setUsuario(this);
    }
}
