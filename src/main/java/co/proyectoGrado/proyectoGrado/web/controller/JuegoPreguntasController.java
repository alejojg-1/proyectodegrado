package co.proyectoGrado.proyectoGrado.web.controller;


import co.proyectoGrado.proyectoGrado.domain.model.JuegoPregunta;
import co.proyectoGrado.proyectoGrado.domain.service.juegopregunta.JuegoPreguntasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping()
    public ResponseEntity<List<JuegoPregunta>> getAll(){
        return new ResponseEntity<>(juegoPreguntasService.getAll(),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/idestudianteJuego/{idestudianteJuego}")
    public ResponseEntity<JuegoPregunta> getById(@PathVariable("idJuegoPregunta") int idJuegoPregunta) {
        return new ResponseEntity<>(juegoPreguntasService.get(idJuegoPregunta), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody List<JuegoPregunta> listaJuegoPregunta) {
        if(juegoPreguntasService.save(listaJuegoPregunta)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }

    }
    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody JuegoPregunta juegoPregunta){
        if(juegoPreguntasService.actualizar(id, juegoPregunta)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(juegoPreguntasService.eliminar(id)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }
}
