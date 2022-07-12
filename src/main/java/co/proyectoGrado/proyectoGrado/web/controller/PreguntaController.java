package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.model.Pregunta;
import co.proyectoGrado.proyectoGrado.domain.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pregunta")

public class PreguntaController {
    private final PreguntaService preguntaService;

    @Autowired
    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @GetMapping()
    public ResponseEntity<List<Pregunta>> getAll(){
        return new ResponseEntity<>(preguntaService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/reto/{idReto}")
    public ResponseEntity<List<Pregunta>> getPorIdReto(@PathVariable("idReto") int idReto){
        return new ResponseEntity<>(preguntaService.obtenerPreguntasPorIdReto(idReto),HttpStatus.OK);
    }

    @GetMapping("/idPreguntas/{idPreguntas}")
    public ResponseEntity<Pregunta> getById(@PathVariable("idPreguntas") int idPreguntas) {
        return new ResponseEntity<>(preguntaService.get(idPreguntas), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody Pregunta pregunta) {

        if(preguntaService.save(pregunta)){
            return new ResponseEntity<>( HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody Pregunta pregunta){

        if(preguntaService.actualizar(id, pregunta)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(preguntaService.eliminar(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
