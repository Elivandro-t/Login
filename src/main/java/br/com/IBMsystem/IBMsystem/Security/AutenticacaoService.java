package br.com.IBMsystem.IBMsystem.Security;
import br.com.IBMsystem.IBMsystem.Models.DTO.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
// class de autenticcao do spring security
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var name =  repository.findByEmail(username);
        if(name==null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return name;
    }
}
