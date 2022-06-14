package co.proyectoGrado.proyectoGrado.web.controller;


import co.proyectoGrado.proyectoGrado.domain.model.JuegoPregunta;
import co.proyectoGrado.proyectoGrado.domain.service.JuegoPreguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/JuegoPregunta")
public class JuegoPreguntasController {
    private final JuegoPreguntasService juegoPreguntasService;

    @Autowired
    public JuegoPreguntasController(JuegoPreguntasService juegoPreguntasService) {
        this.juegoPreguntasService = juegoPreguntasService;
    }
    @GetMapping()
    public ResponseEntity<List<JuegoPregunta>> getAll(){
        return new ResponseEntity<>(juegoPreguntasService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/idestudianteJuego/{idestudianteJuego}")
    public ResponseEntity<JuegoPregunta> getById(@PathVariable("idJuegoPregunta") int idJuegoPregunta) {
        return new ResponseEntity<>(juegoPreguntasService.get(idJuegoPregunta), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody JuegoPregunta juegoPregunta) {
        if(juegoPreguntasService.save(juegoPregunta)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody JuegoPregunta juegoPregunta){
        if(juegoPreguntasService.actualizar(id, juegoPregunta)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(juegoPreguntasService.eliminar(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
