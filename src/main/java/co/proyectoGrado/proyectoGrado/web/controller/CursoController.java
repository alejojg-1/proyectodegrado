package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.proyectoGrado.domain.service.CursoService;
import co.proyectoGrado.proyectoGrado.domain.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Curso")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping()
    public ResponseEntity<List<Curso>> getAll(){
        return new ResponseEntity<>(cursoService.getAll(),HttpStatus.OK);
    }



    @GetMapping("/grado/{grado}")
    public ResponseEntity<Curso> getByGrado(@PathVariable("grado") String grado) {
        return new ResponseEntity<>(cursoService.get(grado), HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody Curso curso) {
        return new ResponseEntity<>(cursoService.save(curso), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody Curso curso){
        return new ResponseEntity<>(cursoService.actualizar(id, curso), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(cursoService.eliminar(id), HttpStatus.OK);
    }

}
