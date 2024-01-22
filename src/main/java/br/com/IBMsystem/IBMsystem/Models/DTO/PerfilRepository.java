package br.com.IBMsystem.IBMsystem.Models.DTO;

import br.com.IBMsystem.IBMsystem.Models.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {
    Optional findOneByName(String name);
}
