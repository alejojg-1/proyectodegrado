package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.proyectoGrado.domain.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping()
    public ResponseEntity<List<Estudiante>> getAll(){
        return new ResponseEntity<>(estudianteService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Estudiante> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(estudianteService.get(email), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody Estudiante estudiante) {
        return new ResponseEntity<>(estudianteService.save(estudiante), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody Estudiante estudiante){
        return new ResponseEntity<>(estudianteService.actualizar(id, estudiante), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(estudianteService.eliminar(id), HttpStatus.OK);
    }

}
