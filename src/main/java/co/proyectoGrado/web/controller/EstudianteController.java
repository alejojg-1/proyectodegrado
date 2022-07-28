package co.proyectoGrado.web.controller;

import co.proyectoGrado.domain.dto.DtoEstudianteRelacionado;
import co.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.domain.service.estudiante.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping()
    public ResponseEntity<List<Estudiante>> getAll(){
        return new ResponseEntity<>(estudianteService.getAll(),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<List<DtoEstudianteRelacionado>> obtenerEstudiantesPorIdCurso(@PathVariable("idCurso") int idCurso){
        return new ResponseEntity<>(estudianteService.getByIdCurso(idCurso),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @GetMapping("/email/{email}")
    public ResponseEntity<Estudiante> getByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(estudianteService.get(email), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody Estudiante estudiante) {

        if(estudianteService.save(estudiante)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCENTE,ROLE_ESTUDIANTE')")
    @PutMapping("/actualizar")
    public ResponseEntity<Boolean> actualizar(@RequestBody Estudiante estudiante){
        if(estudianteService.actualizar(estudiante)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(estudianteService.eliminar(id)){
            return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
        }
    }

}
