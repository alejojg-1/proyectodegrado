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

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody EstudianteJuegoRespuesta estudianteJuegoRespuesta) {

        if(EstudianteJuegoRespuestasService.save(estudianteJuegoRespuesta)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody EstudianteJuegoRespuesta estudianteJuegoRespuesta){

        if(estudianteJuegoRespuestasService.actualizar(id, estudianteJuegoRespuesta)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(estudianteJuegoRespuestasService.eliminar(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
