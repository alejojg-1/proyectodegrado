package co.proyectoGrado.web.controller;


import co.proyectoGrado.domain.dto.DtoReporteEstudiante;
import co.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.domain.service.estudiantejuego.EstudianteJuegoService;
import co.proyectoGrado.domain.service.estudiantejuego.ReporteEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/estudianteJuego")

public class EstudianteJuegoController {
    private final EstudianteJuegoService estudianteJuegoService;
    private final ReporteEstudianteService reporteEstudianteService;

    @Autowired
    public EstudianteJuegoController(EstudianteJuegoService estudianteJuegoService, ReporteEstudianteService reporteEstudianteService) {
        this.estudianteJuegoService = estudianteJuegoService;
        this.reporteEstudianteService = reporteEstudianteService;
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping()
    public ResponseEntity<List<EstudianteJuego>> getAll(){
        return new ResponseEntity<>(estudianteJuegoService.getAll(),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @GetMapping("/reporte/reto/{idReto}")
    public ResponseEntity<List<DtoReporteEstudiante>> obtenerReporteCalificacionesPorReto(@PathVariable("idReto") int idReto){
        return new ResponseEntity<>(reporteEstudianteService.ejecutar(idReto),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/idestudianteJuego/{idestudianteJuego}")
    public ResponseEntity<EstudianteJuego> getById(@PathVariable("idestudianteJuego") int idestudianteJuego) {
        return new ResponseEntity<>(estudianteJuegoService.get(idestudianteJuego), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @PostMapping("/save")
    public ResponseEntity<EstudianteJuego> save(@RequestBody EstudianteJuego estudianteJuego) {
        try {
            return new ResponseEntity<>( estudianteJuegoService.save(estudianteJuego), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody EstudianteJuego estudianteJuego){
        if(estudianteJuegoService.actualizar(id, estudianteJuego)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(estudianteJuegoService.eliminar(id)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }
}

