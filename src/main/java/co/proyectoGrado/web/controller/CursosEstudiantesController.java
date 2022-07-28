package co.proyectoGrado.web.controller;

import co.proyectoGrado.domain.dto.DtoRelacionEstudianteCurso;
import co.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.domain.service.cursoestudiantes.CursosEstudiantesService;
import co.proyectoGrado.domain.service.cursoestudiantes.RelacionarCursoAEstudiantePorCodigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso-estudiante")
public class CursosEstudiantesController {
    private final CursosEstudiantesService cursosEstudiantesService;
    private final RelacionarCursoAEstudiantePorCodigoService relacionarCursoAEstudiantePorCodigoService;

    @Autowired
    public CursosEstudiantesController(CursosEstudiantesService cursosEstudiantesService,
                                       RelacionarCursoAEstudiantePorCodigoService relacionarCursoAEstudiantePorCodigoService) {
        this.cursosEstudiantesService = cursosEstudiantesService;
        this.relacionarCursoAEstudiantePorCodigoService = relacionarCursoAEstudiantePorCodigoService;
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping()
    public ResponseEntity<List<CursoEstudiante>> getAll() {

        return new ResponseEntity<>(cursosEstudiantesService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/id-curso-estudiante/{idcursoestudiantes}")
    public ResponseEntity<CursoEstudiante> getById(@PathVariable("idcursoestudiantes") int idCursoEstudiantes) {
        return new ResponseEntity<>(cursosEstudiantesService.get(idCursoEstudiantes), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody DtoRelacionEstudianteCurso dtoRelacionEstudianteCurso) {
        if (relacionarCursoAEstudiantePorCodigoService.ejecutar(dtoRelacionEstudianteCurso)) {
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody CursoEstudiante cursoestudiante) {
        if (cursosEstudiantesService.actualizar(id, cursoestudiante)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id) {
        if (cursosEstudiantesService.eliminar(id)) {
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }
}
