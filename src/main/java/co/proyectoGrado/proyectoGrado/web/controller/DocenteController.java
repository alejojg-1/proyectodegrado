package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.proyectoGrado.domain.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class DocenteController {
    private final DocenteService docenteService;

    @Autowired
    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    //Quitar anotacion e ubicar en el lugar que debe de ir
    //@PreAuthorize("hasRole('ROLE_DOCENTE')")
    @GetMapping()
    public ResponseEntity<List<Docente>> getAll(){
        return new ResponseEntity<>(docenteService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Docente> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(docenteService.get(email), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody Docente docente) {
        return new ResponseEntity<>(docenteService.save(docente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody Docente docente){
        return new ResponseEntity<>(docenteService.actualizar(id, docente), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(docenteService.eliminar(id), HttpStatus.OK);
    }

}
