package co.proyectoGrado.proyectoGrado.domain.service.estudiante;

import co.proyectoGrado.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.proyectoGrado.domain.repository.EstudianteRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteEntity;
import co.proyectoGrado.proyectoGrado.domain.service.cursoestudiantes.CursosEstudiantesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    private final CursosEstudiantesService cursosEstudiantesService;
    private final EstudianteRepository estudianteRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository,
                             CursosEstudiantesService cursosEstudiantesService,
                             BCryptPasswordEncoder passwordEncoder) {
        this.estudianteRepository = estudianteRepository;
        this.passwordEncoder =passwordEncoder;
        this.cursosEstudiantesService = cursosEstudiantesService;
    }

    public List<Estudiante> getAll(){
        return estudianteRepository.getAll();
    }
    public Estudiante get(String email) {
        return estudianteRepository.get(email);
    }

    public List<Estudiante> getByIdCurso(int idCurso) {
        List<Integer> idsEstudiantes = cursosEstudiantesService.getByIdCurso(idCurso).stream()
                .map(CursoEstudiante::getIdEstudiantes).collect(Collectors.toList());
        return estudianteRepository.getByIds(idsEstudiantes);
    }

    public Estudiante getById(Integer idEstudiante) {
        return estudianteRepository.getById(idEstudiante);
    }
    public boolean save(Estudiante estudiante) {
        estudiante.setContrasena(encodeContrasena(estudiante.getContrasena()));
        EstudianteEntity contenido = mapper.map(estudiante, EstudianteEntity.class);
        try {
            estudianteRepository.save(estudiante);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(Estudiante estudiante) {
        return  estudianteRepository.actualizar(estudiante);
    }

    public boolean eliminar(int id){

        return estudianteRepository.delete(id);
    }

    private String encodeContrasena(String contrasena){
        return passwordEncoder.encode(contrasena);
    }

    private String decoderContrasena(String contrasena){
        //¿Cómo decodificar?
        return passwordEncoder.encode(contrasena);
    }


}