package co.proyectoGrado.proyectoGrado.domain.service.cursoestudiantes;

import co.proyectoGrado.proyectoGrado.domain.dto.DtoRelacionEstudianteCurso;
import co.proyectoGrado.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.proyectoGrado.domain.service.estudiante.EstudianteService;
import co.proyectoGrado.proyectoGrado.domain.service.curso.CursoService;
import org.springframework.stereotype.Service;

@Service
public class RelacionarCursoAEstudiantePorCodigoService {
    private final EstudianteService estudianteService;
    private final CursoService cursoService;
    private final CursosEstudiantesService cursosEstudiantesService;

    public RelacionarCursoAEstudiantePorCodigoService(EstudianteService estudianteService,
                                                      CursoService cursoService,
                                                      CursosEstudiantesService cursosEstudiantesService) {
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
        this.cursosEstudiantesService = cursosEstudiantesService;
    }

    public Boolean ejecutar(DtoRelacionEstudianteCurso dtoRelacionEstudianteCurso){
        Estudiante estudiante = estudianteService.get(dtoRelacionEstudianteCurso.getCorreoEstudiante());
        Curso curso = cursoService.getByCodigo(dtoRelacionEstudianteCurso.getCodigoCurso());

        return cursosEstudiantesService.save(new CursoEstudiante(null,
                estudiante.getIdEstudiante(),curso.getIdCursos() ));
    }
}
