package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.proyectoGrado.domain.model.Docente;
import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.service.CursosEstudiantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/CursoEstudiante")
public class CursosEstudiantesController {
    private final CursosEstudiantesService cursosEstudiantesService;

    @Autowired
    public CursosEstudiantesController(CursosEstudiantesService cursosEstudiantesService) {
        this. cursosEstudiantesService = cursosEstudiantesService;
    }
    @GetMapping()
    public ResponseEntity<List<CursoEstudiante>> getAll(){
        return new ResponseEntity<>(cursosEstudiantesService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/id-curso-estudiante/{idcursoestudiantes}")
    public ResponseEntity<CursoEstudiante> getById(@PathVariable("idcursoestudiantes")int idCursoEstudiantes) {
        return new ResponseEntity<>(cursosEstudiantesService.get(idCursoEstudiantes), HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody CursoEstudiante  cursosEstudiantes) {
        return new ResponseEntity<>(cursosEstudiantesService.save(cursosEstudiantes), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody CursoEstudiante cursoestudiante){
        return new ResponseEntity<>(cursosEstudiantesService.actualizar(id, cursoestudiante), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(cursosEstudiantesService.eliminar(id), HttpStatus.OK);
    }
}
