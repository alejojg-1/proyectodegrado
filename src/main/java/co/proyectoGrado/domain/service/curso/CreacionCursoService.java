package co.proyectoGrado.domain.service.curso;

import co.proyectoGrado.domain.dto.DtoCreacionCurso;
import co.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.domain.service.cursodocente.CursoDocenteService;
import co.proyectoGrado.domain.service.docente.DocenteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CreacionCursoService {

    private final CursoService cursoService;
    private final DocenteService docenteService;
    private final CursoDocenteService cursoDocenteService;

    public CreacionCursoService(CursoService cursoService,
                                DocenteService docenteService,
                                CursoDocenteService cursoDocenteService) {
        this.cursoService = cursoService;
        this.docenteService = docenteService;
        this.cursoDocenteService = cursoDocenteService;
    }

    public Boolean ejecutar (DtoCreacionCurso dtoCreacionCurso){
        Docente docente = docenteService.get(dtoCreacionCurso.getCorreoDocente());
        Curso curso =  cursoService.save(
                new Curso(null,
                dtoCreacionCurso.getGrado(),
                dtoCreacionCurso.getNombre(),
                generarCodigoCurso(),
                new ArrayList<>()));

        return cursoDocenteService.save(new CursoDocente(null,docente.getIdDocente(),
                curso.getIdCursos(),Boolean.TRUE));
    }

    private String generarCodigoCurso(){
        return java.util.UUID.randomUUID().toString();
    }
}
