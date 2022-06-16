package co.proyectoGrado.proyectoGrado.domain.service.curso;

import co.proyectoGrado.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.proyectoGrado.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObtenerCursosPorUsuarioService {

    private static final String ESCENARIO_ESTUDIANTE = "ROLE_ESTUDIANTE";
    private static final String ESCENARIO_DOCENTE = "ROLE_DOCENTE";
    private final CursoDocenteRepository cursoDocenteRepository;
    private final CursosEstudiantesRepository cursosEstudiantesRepository;
    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;
    private final DocenteRepository docenteRepository;

    @Autowired
    public ObtenerCursosPorUsuarioService(CursoDocenteRepository cursoDocenteRepository,
                                          CursosEstudiantesRepository cursosEstudiantesRepository,
                                          CursoRepository cursoRepository, EstudianteRepository estudianteRepository, DocenteRepository docenteRepository) {
        this.cursoDocenteRepository = cursoDocenteRepository;
        this.cursosEstudiantesRepository = cursosEstudiantesRepository;
        this.cursoRepository = cursoRepository;
        this.estudianteRepository = estudianteRepository;
        this.docenteRepository = docenteRepository;
    }

    public List<Curso> ejecutar(String email, String rolUsuario) {
        List<Curso> listaCursos = new ArrayList<>();
        if (ESCENARIO_ESTUDIANTE.equals(rolUsuario)) {
            listaCursos.addAll(ejecutarEscenarioEstudiante(email));
        }
        if (ESCENARIO_DOCENTE.equals(rolUsuario)) {
            listaCursos.addAll(ejecutarEscenarioDocente(email));
        }
        return listaCursos;
    }

    private List<Curso> ejecutarEscenarioEstudiante(String email) {

        List<CursoEstudiante> listaCursoEstudiantes = cursosEstudiantesRepository
                .getByIdEstudiante(estudianteRepository.get(email).getIdEstudiante());
        List<Integer> IdsCursos = listaCursoEstudiantes.stream()
                .map(CursoEstudiante::getIdCursos).collect(Collectors.toList());
        return cursoRepository.getByIdsCursos(IdsCursos);
    }

    private List<Curso> ejecutarEscenarioDocente(String email) {
        List<CursoDocente> listaCursoDocente = cursoDocenteRepository
                .getByIdDocente(docenteRepository.get(email).getIdDocente());
        List<Integer> IdsCursos = listaCursoDocente.stream()
                .map(CursoDocente::getIdCurso).collect(Collectors.toList());

        return cursoRepository.getByIdsCursos(IdsCursos);
    }
}
