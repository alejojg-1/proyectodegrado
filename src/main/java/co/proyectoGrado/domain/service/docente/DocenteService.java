package co.proyectoGrado.domain.service.docente;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.domain.service.estudiante.EstudianteService;
import co.proyectoGrado.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService {
    private final DocenteRepository docenteRepository;
    @Autowired
    private final EstudianteService estudianteService;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DocenteService(DocenteRepository docenteRepository,
                          @Lazy EstudianteService estudianteService,
                          BCryptPasswordEncoder passwordEncoder) {
        this.docenteRepository = docenteRepository;
        this.estudianteService = estudianteService;
        this.passwordEncoder =passwordEncoder;
    }

    public List<Docente> getAll(){
        return docenteRepository.getAll();
    }
    public Docente get(String email) {
        return docenteRepository.get(email);
    }

    public boolean save(Docente docente) {

        if(estudianteService.get(docente.getCorreo()) != null
                && estudianteService.get(docente.getCorreo()).getCorreo()
                .equals(docente.getCorreo())){
            return Boolean.FALSE;
        }
        docente.setContrasena(encodeContrasena(docente.getContrasena()));
        try {
            docenteRepository.save(docente);
            return Boolean.TRUE;
        } catch (ExcepcionDeProceso e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(Docente docente) {
    return  docenteRepository.actualizar(docente);
    }

    public boolean eliminar(int id){

        return docenteRepository.delete(id);
    }

    private String encodeContrasena(String contrasena){
        return passwordEncoder.encode(contrasena);
    }

}
