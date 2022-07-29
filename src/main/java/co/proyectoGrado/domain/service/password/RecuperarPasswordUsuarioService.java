package co.proyectoGrado.domain.service.password;

import co.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.domain.service.correo.EnviarCorreoService;
import co.proyectoGrado.domain.service.docente.DocenteService;
import co.proyectoGrado.domain.service.estudiante.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class RecuperarPasswordUsuarioService {

    private final EstudianteService estudianteService;
    private final DocenteService docenteService;
    private final EnviarCorreoService enviarCorreoService;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public RecuperarPasswordUsuarioService(EstudianteService estudianteService,
                                           DocenteService docenteService,
                                           EnviarCorreoService enviarCorreoService,
                                           BCryptPasswordEncoder passwordEncoder) {

        this.estudianteService = estudianteService;
        this.docenteService = docenteService;
        this.enviarCorreoService = enviarCorreoService;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean ejecutar(String email, int identificacion) {
        Estudiante estudiante = estudianteService.get(email);
        Docente docente = docenteService.get(email);
        if (estudiante != null || docente != null) {
          return  enviarCorreoYRestablecerPasswordUsuario(estudiante, docente, identificacion);
        }
        return Boolean.FALSE;
    }

    private Boolean enviarCorreoYRestablecerPasswordUsuario(Estudiante estudiante, Docente docente, int identificacion) {
        String password = java.util.UUID.randomUUID().toString();
        Boolean restablecerEstudiante = Boolean.FALSE;
        Boolean restablecerDocente = Boolean.FALSE;
        if (estudiante != null) {
            if (estudiante.getIdentificacion() == identificacion) {
                restablecerEstudiante = Boolean.TRUE;
                estudiante.setContrasena(encodeContrasena(password));
                estudianteService.actualizar(estudiante);
                enviarCorreoService(estudiante.getCorreo(), password);

            }

        } else {
            if (docente.getIdentificacion() == identificacion) {
                restablecerDocente = Boolean.TRUE;
                docente.setContrasena(encodeContrasena(password));
                docenteService.actualizar(docente);
                enviarCorreoService(docente.getCorreo(), password);
            }
        }
        return restablecerEstudiante || restablecerDocente ? Boolean.TRUE: Boolean.FALSE;
    }

    private void enviarCorreoService(String email, String contrasena) {
        try {
            enviarCorreoService.enviarCorreo(email, contrasena);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String encodeContrasena(String contrasena) {
        return passwordEncoder.encode(contrasena);
    }
}
