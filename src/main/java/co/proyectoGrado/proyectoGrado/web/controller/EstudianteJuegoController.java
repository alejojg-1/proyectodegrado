package co.proyectoGrado.proyectoGrado.web.controller;


import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.proyectoGrado.domain.service.EstudianteJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/estudianteJuego")

public class EstudianteJuegoController {
    private final EstudianteJuegoService estudianteJuegoService;

    @Autowired
    public EstudianteJuegoController(EstudianteJuegoService estudianteJuegoService) {
        this.estudianteJuegoService = estudianteJuegoService;
    }

    @GetMapping()
    public ResponseEntity<List<EstudianteJuego>> getAll(){
        return new ResponseEntity<>(estudianteJuegoService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/idestudianteJuego/{idestudianteJuego}")
    public ResponseEntity<EstudianteJuego> getById(@PathVariable("idestudianteJuego") int idestudianteJuego) {
        return new ResponseEntity<>(estudianteJuegoService.get(idestudianteJuego), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody EstudianteJuego estudianteJuego) {
        return new ResponseEntity<>(estudianteJuegoService.save(estudianteJuego), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody EstudianteJuego estudianteJuego){
        return new ResponseEntity<>(estudianteJuegoService.actualizar(id, estudianteJuego), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(estudianteJuegoService.eliminar(id), HttpStatus.OK);
    }
}

