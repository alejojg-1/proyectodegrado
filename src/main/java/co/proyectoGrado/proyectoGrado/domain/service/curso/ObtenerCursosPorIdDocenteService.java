package co.proyectoGrado.proyectoGrado.domain.service.curso;

import co.proyectoGrado.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoDocenteRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObtenerCursosPorIdDocenteService {

    private final CursoDocenteRepository cursoDocenteRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public ObtenerCursosPorIdDocenteService(CursoDocenteRepository cursoDocenteRepository,CursoRepository cursoRepository){
        this.cursoDocenteRepository = cursoDocenteRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> ejecutar(int idDocente){
        List<CursoDocente>  listaCursoDocente = cursoDocenteRepository.getByIdDocente(idDocente);
        List<Integer> IdsCursos = obtenerIdsCursos(listaCursoDocente);
        return  cursoRepository.getByIdsCursos(IdsCursos);
    }

    private List<Integer> obtenerIdsCursos(List<CursoDocente> listaCursoDocente){
        return listaCursoDocente.stream().map(CursoDocente::getIdCurso).collect(Collectors.toList());
    }
}
