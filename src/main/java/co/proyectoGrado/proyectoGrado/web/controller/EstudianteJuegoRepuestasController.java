package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.dto.DtoCreacionReto;
import co.proyectoGrado.proyectoGrado.domain.dto.DtoRespuestasReto;
import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import co.proyectoGrado.proyectoGrado.domain.service.estudiantejuego.AccionesCrearEstudianteJuegoSegunEscenarioService;
import co.proyectoGrado.proyectoGrado.domain.service.estudiantejuegorespuesta.EstudianteJuegoRespuestasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudianteJuegoRespuestas")
public class EstudianteJuegoRepuestasController {

    private final EstudianteJuegoRespuestasService estudianteJuegoRespuestasService;
    private final AccionesCrearEstudianteJuegoSegunEscenarioService accionesCrearEstudianteJuegoSegunEscenarioService;
    @Autowired
    public EstudianteJuegoRepuestasController(EstudianteJuegoRespuestasService estudianteJuegoRespuestasService,
                                              AccionesCrearEstudianteJuegoSegunEscenarioService accionesCrearEstudianteJuegoSegunEscenarioService) {
        this.estudianteJuegoRespuestasService = estudianteJuegoRespuestasService;
        this.accionesCrearEstudianteJuegoSegunEscenarioService = accionesCrearEstudianteJuegoSegunEscenarioService;
    }

    @GetMapping("/idestudianteJuego/{idestudianteJuego}")
    public ResponseEntity<EstudianteJuegoRespuesta> getById(@PathVariable("idestudianteJuego") int idestudianteJuego) {
        return new ResponseEntity<>(estudianteJuegoRespuestasService.get(idestudianteJuego), HttpStatus.OK);
    }

    @PostMapping("/resultado-reto")
    public ResponseEntity<Double> puntuacionSegunEscenario(@RequestBody DtoRespuestasReto creacionRespuestasReto) {
        try {
            return new ResponseEntity<>(accionesCrearEstudianteJuegoSegunEscenarioService.
                    ejecutar(creacionRespuestasReto.getListaEstudianteJuegoRespuestas(),
                    creacionRespuestasReto.getCorreoEstudiante()),HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody List <EstudianteJuegoRespuesta> listaEstudianteJuegoRespuesta) {

        if(estudianteJuegoRespuestasService.save(listaEstudianteJuegoRespuesta)){
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
