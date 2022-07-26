package co.proyectoGrado.proyectoGrado.web.controller;
import co.proyectoGrado.proyectoGrado.domain.dto.DtoCreacionCurso;
import co.proyectoGrado.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.proyectoGrado.domain.service.curso.CreacionCursoService;
import co.proyectoGrado.proyectoGrado.domain.service.curso.CursoService;
import co.proyectoGrado.proyectoGrado.domain.service.curso.ObtenerCursosPorUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<List<Curso>> getAll(){
        return new ResponseEntity<>(cursoService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<Curso> getById(@PathVariable("idCurso") int idCurso){
        return new ResponseEntity<>(cursoService.getById(idCurso),HttpStatus.OK);
    }

    @GetMapping("/grado/{grado}")
    public ResponseEntity<Curso> getByGrado(@PathVariable("grado") String grado) {
        return new ResponseEntity<>(cursoService.get(grado), HttpStatus.OK);
    }

    @GetMapping("/obtener-segun-escenario")
    public ResponseEntity<List<Curso>> getCursosPorUsuarioYRol(@RequestParam("email") String email,
                                                               @RequestParam("rolUsuario") String rolUsuario) {
        return new ResponseEntity<>(obtenerCursosPorUsuarioService.ejecutar(email,rolUsuario), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody DtoCreacionCurso dtoCreacionCurso) {
        if(cracionCursoService.ejecutar(dtoCreacionCurso)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody Curso curso){
        if(cursoService.actualizar(id, curso)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(cursoService.eliminar(id)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }
}
