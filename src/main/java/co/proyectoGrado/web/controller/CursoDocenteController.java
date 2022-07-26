package co.proyectoGrado.web.controller;

import co.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.domain.service.cursodocente.CursoDocenteService;
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

        if(cursodocenteService.save(cursodocente)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody CursoDocente cursoDocente){
        if(cursodocenteService.actualizar(id, cursoDocente)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(cursodocenteService.eliminar(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
