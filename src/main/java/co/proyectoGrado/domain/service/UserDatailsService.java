package co.proyectoGrado.domain.service;

import co.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.repository.DocenteRepository;
import co.proyectoGrado.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDatailsService implements UserDetailsService {

    private final EstudianteRepository estudianteRepository;
    private final DocenteRepository docenteRepository;
    private static final String ROL_ESTUDIANTE ="ESTUDIANTE";
    private static final String ROL_DOCENTE ="DOCENTE";

    @Autowired
    public UserDatailsService(EstudianteRepository estudianteRepository, DocenteRepository docenteRepository) {
        this.estudianteRepository = estudianteRepository;
        this.docenteRepository = docenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        Estudiante estudiante =  estudianteRepository.get(email);
        Docente docente = docenteRepository.get(email);
        if(estudiante==null && docente == null) {
            //Validar como enviar una excepción que diga algo
            throw new UsernameNotFoundException(email);
        }
        return getUser(estudiante, docente);
    }

    private UserDetails getUser(Estudiante estudiante, Docente docente){
        if(estudiante!=null){
            return User.withUsername(estudiante.getCorreo())
                    .password(estudiante.getContrasena())
                    .authorities(getAuthorities(ROL_ESTUDIANTE)).build();
        }else{
            return User.withUsername(docente.getCorreo())
                    .password(docente.getContrasena())
                    .authorities(getAuthorities(ROL_DOCENTE)).build();
        }
    }

    private static Set<? extends GrantedAuthority> getAuthorities(String rol) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + rol));
        return authorities;
    }

}
