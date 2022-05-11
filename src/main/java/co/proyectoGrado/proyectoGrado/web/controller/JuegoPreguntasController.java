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
        return new ResponseEntity<>(juegoPreguntasService.save(juegoPregunta), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody JuegoPregunta juegoPregunta){
        return new ResponseEntity<>(juegoPreguntasService.actualizar(id, juegoPregunta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(juegoPreguntasService.eliminar(id), HttpStatus.OK);
    }
}
