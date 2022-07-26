package co.proyectoGrado.web.controller;

import co.proyectoGrado.domain.model.Pregunta;
import co.proyectoGrado.domain.service.pregunta.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping()
    public ResponseEntity<List<Pregunta>> getAll(){
        return new ResponseEntity<>(preguntaService.getAll(),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/reto/{idReto}")
    public ResponseEntity<List<Pregunta>> getPorIdReto(@PathVariable("idReto") int idReto){
        return new ResponseEntity<>(preguntaService.obtenerPreguntasPorIdReto(idReto),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/idPreguntas/{idPreguntas}")
    public ResponseEntity<Pregunta> getById(@PathVariable("idPreguntas") int idPreguntas) {
        return new ResponseEntity<>(preguntaService.get(idPreguntas), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @PostMapping("/save")
    public ResponseEntity<List<Pregunta>> save(@RequestBody List<Pregunta> preguntas) {
       return new ResponseEntity<>(preguntaService.save(preguntas),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
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
