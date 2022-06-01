package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.service.CursoDocenteService;
import co.proyectoGrado.proyectoGrado.domain.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/CursoDocente")
public class CursoDocenteController {
    private final CursoDocenteService cursodocenteService;

    @Autowired
    public CursoDocenteController(CursoDocenteService cursodocenteService) {
        this.cursodocenteService = cursodocenteService;
    }

    @GetMapping()
    public ResponseEntity<List<CursoDocente>> getAll(){
        return new ResponseEntity<>(cursodocenteService.getAll(),HttpStatus.OK);
    }


    @GetMapping("/iddocentes/{iddocentes}")
    public ResponseEntity<CursoDocente> getById(@PathVariable("iddocentes")int iddocentes) {
        return new ResponseEntity<>(cursodocenteService.get(iddocentes), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody CursoDocente cursodocente) {
        return new ResponseEntity<>(cursodocenteService.save(cursodocente), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody CursoDocente cursoDocente){
        return new ResponseEntity<>(cursodocenteService.actualizar(id, cursoDocente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(cursodocenteService.eliminar(id), HttpStatus.OK);
    }


}
