package co.proyectoGrado.domain.service.password;

import co.proyectoGrado.domain.dto.DtoCambioPassowordUsuario;
import co.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.domain.service.docente.DocenteService;
import co.proyectoGrado.domain.service.estudiante.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CambioPasswordUsuarioService {

    private final EstudianteService estudianteService;
    private final DocenteService docenteService;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public CambioPasswordUsuarioService(EstudianteService estudianteService,
                                        DocenteService docenteService, BCryptPasswordEncoder passwordEncoder) {
        this.estudianteService = estudianteService;
        this.docenteService = docenteService;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean ejecutar(DtoCambioPassowordUsuario dtoCambioPassowordUsuario) {
        Estudiante estudiante = estudianteService.get(dtoCambioPassowordUsuario.getCorreo());
        Docente docente = docenteService.get(dtoCambioPassowordUsuario.getCorreo());
        if (estudiante != null || docente != null) {
            return enviarCorreoYRestablecerPasswordUsuario(estudiante, docente, dtoCambioPassowordUsuario.getContrasena());
        }
        return Boolean.FALSE;
    }

    private Boolean enviarCorreoYRestablecerPasswordUsuario(Estudiante estudiante,
                                                            Docente docente,
                                                            String password) {
        Boolean cambioPasswordEstudiante = Boolean.FALSE;
        Boolean cambioPasswordDocente = Boolean.FALSE;
        if (estudiante != null) {
            estudiante.setContrasena(encodeContrasena(password));
            estudianteService.actualizar(estudiante);
            cambioPasswordEstudiante = Boolean.TRUE;

        } else {
            docente.setContrasena(encodeContrasena(password));
            docenteService.actualizar(docente);
            cambioPasswordDocente = Boolean.TRUE;
        }
        return cambioPasswordEstudiante || cambioPasswordDocente ? Boolean.TRUE : Boolean.FALSE;
    }

    private String encodeContrasena(String contrasena) {
        return passwordEncoder.encode(contrasena);
    }
}
