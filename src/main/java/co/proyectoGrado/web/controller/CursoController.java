package co.proyectoGrado.web.controller;
import co.proyectoGrado.domain.dto.DtoCreacionCurso;
import co.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.domain.service.curso.CreacionCursoService;
import co.proyectoGrado.domain.service.curso.CursoService;
import co.proyectoGrado.domain.service.curso.ObtenerCursosPorUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    private final CursoService cursoService;
    private final ObtenerCursosPorUsuarioService obtenerCursosPorUsuarioService;
    private final CreacionCursoService cracionCursoService;

    @Autowired
    public CursoController(CursoService cursoService,
                           ObtenerCursosPorUsuarioService obtenerCursosPorUsuarioService,
                           CreacionCursoService cracionCursoService) {
        this.cursoService = cursoService;
        this.obtenerCursosPorUsuarioService = obtenerCursosPorUsuarioService;
        this.cracionCursoService = cracionCursoService;
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping()
    public ResponseEntity<List<Curso>> getAll(){
        return new ResponseEntity<>(cursoService.getAll(),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/{idCurso}")
    public ResponseEntity<Curso> getById(@PathVariable("idCurso") int idCurso){
        return new ResponseEntity<>(cursoService.getById(idCurso),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/grado/{grado}")
    public ResponseEntity<Curso> getByGrado(@PathVariable("grado") String grado) {
        return new ResponseEntity<>(cursoService.get(grado), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/obtener-segun-escenario")
    public ResponseEntity<List<Curso>> getCursosPorUsuarioYRol(@RequestParam("email") String email,
                                                               @RequestParam("rolUsuario") String rolUsuario) {
        return new ResponseEntity<>(obtenerCursosPorUsuarioService.ejecutar(email,rolUsuario), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody DtoCreacionCurso dtoCreacionCurso) {
        if(cracionCursoService.ejecutar(dtoCreacionCurso)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody Curso curso){
        if(cursoService.actualizar(id, curso)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(cursoService.eliminar(id)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }
}
