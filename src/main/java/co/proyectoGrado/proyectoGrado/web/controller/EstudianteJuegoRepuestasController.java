package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import co.proyectoGrado.proyectoGrado.domain.service.EstudianteJuegoRespuestasService;
import co.proyectoGrado.proyectoGrado.domain.service.EstudianteJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudianteJuegoRespuestas")
public class EstudianteJuegoRepuestasController {

    private final EstudianteJuegoRespuestasService estudianteJuegoRespuestasService;

    @Autowired
    public EstudianteJuegoRepuestasController(EstudianteJuegoRespuestasService estudianteJuegoRespuestasService) {
        this.estudianteJuegoRespuestasService = estudianteJuegoRespuestasService;
    }

    @GetMapping("/idestudianteJuego/{idestudianteJuego}")
    public ResponseEntity<EstudianteJuegoRespuesta> getById(@PathVariable("idestudianteJuego") int idestudianteJuego) {
        return new ResponseEntity<>(estudianteJuegoRespuestasService.get(idestudianteJuego), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Boolean> save(@RequestBody EstudianteJuegoRespuesta estudianteJuegoRespuesta) {
        return new ResponseEntity<>(estudianteJuegoRespuestasService.save(estudianteJuegoRespuesta), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody EstudianteJuegoRespuesta estudianteJuegoRespuesta){
        return new ResponseEntity<>(estudianteJuegoRespuestasService.actualizar(id, estudianteJuegoRespuesta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(estudianteJuegoRespuestasService.eliminar(id), HttpStatus.OK);
    }

}
