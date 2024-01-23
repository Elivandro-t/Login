package br.com.IBMsystem.IBMsystem.Models.DTO;

import br.com.IBMsystem.IBMsystem.Models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios,String> {


    Usuarios findByEmail(String username);
    @Query("select s from Usuarios s where s.email = :email")
    Optional pegandoUsuarioExistente(String email);


}
