package co.proyectoGrado.domain.service.estudiante;

import co.proyectoGrado.domain.dto.DtoEstudianteRelacionado;
import co.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.domain.service.cursoestudiantes.CursosEstudiantesService;
import co.proyectoGrado.domain.service.docente.DocenteService;
import co.proyectoGrado.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    private final CursosEstudiantesService cursosEstudiantesService;
    private final EstudianteRepository estudianteRepository;
    @Autowired
    private final DocenteService docenteService;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository,
                             @Lazy CursosEstudiantesService cursosEstudiantesService,
                             @Lazy DocenteService docenteService,
                             BCryptPasswordEncoder passwordEncoder) {
        this.estudianteRepository = estudianteRepository;
        this.docenteService = docenteService;
        this.passwordEncoder =passwordEncoder;
        this.cursosEstudiantesService = cursosEstudiantesService;
    }

    public List<Estudiante> getAll(){
        return estudianteRepository.getAll();
    }

    public Estudiante get(String email) {
        return estudianteRepository.get(email);
    }

    public List<DtoEstudianteRelacionado> getByIdCurso(int idCurso) {
        List<DtoEstudianteRelacionado> dtoEstudianteRelacionados = new ArrayList<>();
        List<CursoEstudiante> listaCursoEstudiante = cursosEstudiantesService.getByIdCurso(idCurso);
        List<Integer> idsEstudiantes = listaCursoEstudiante.stream()
                .map(CursoEstudiante::getIdEstudiantes).collect(Collectors.toList());
        List<Estudiante> estudiante = estudianteRepository.getByIds(idsEstudiantes);

        estudiante.forEach(estudianteObtenido ->{
            CursoEstudiante cursoEstudiante = listaCursoEstudiante.stream().filter(relacion ->
                            relacion.getIdEstudiantes() == estudianteObtenido.getIdEstudiante())
                    .findFirst().orElse(null);
            if(cursoEstudiante != null){
                dtoEstudianteRelacionados.add(new DtoEstudianteRelacionado(cursoEstudiante.getIdCursoEstudiante(),
                        estudianteObtenido.getIdEstudiante(),estudianteObtenido.getNombre(),estudianteObtenido.getApellido(),
                        estudianteObtenido.getIdentificacion(),estudianteObtenido.getCorreo()));
            }
        });
        return dtoEstudianteRelacionados;
    }

    public Estudiante getById(Integer idEstudiante) {
        return estudianteRepository.getById(idEstudiante);
    }

    public boolean save(Estudiante estudiante) {
        if(docenteService.get(estudiante.getCorreo()) != null
                && docenteService.get(estudiante.getCorreo()).getCorreo()
                .equals(estudiante.getCorreo())){
            return Boolean.FALSE;
        }
        estudiante.setContrasena(encodeContrasena(estudiante.getContrasena()));
        try {
            estudianteRepository.save(estudiante);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(Estudiante estudiante) {
        if(docenteService.get(estudiante.getCorreo()) != null
                && docenteService.get(estudiante.getCorreo()).getCorreo()
                .equals(estudiante.getCorreo())){
            return Boolean.FALSE;
        }
        return  estudianteRepository.actualizar(estudiante);
    }

    public boolean eliminar(int id){
        return estudianteRepository.delete(id);
    }

    private String encodeContrasena(String contrasena){
        return passwordEncoder.encode(contrasena);
    }

}