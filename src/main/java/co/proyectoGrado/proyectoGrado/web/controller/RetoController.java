package co.proyectoGrado.proyectoGrado.web.controller;


import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.service.RetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reto")
public class RetoController {
    private final RetoService retoService;

    @Autowired
    public RetoController(RetoService retoService) {
        this.retoService = retoService;
    }

    @GetMapping()
    public ResponseEntity<List<Reto>> obtenerAll(){

        return new ResponseEntity<>(retoService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/curso/{idcurso}")
    public ResponseEntity<List<Reto>> obtenerRetosPorCursoId(@PathVariable("idcurso") int idCurso){
        return new ResponseEntity<>(retoService.getByCursoId(idCurso),HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<Reto> obtenerPorTipo(@PathVariable("tipo") String tipo) {
        return new ResponseEntity<>(retoService.get(tipo), HttpStatus.OK);
    }

    @GetMapping("/curso/{idcurso}/tipo/{tipo}")
    public ResponseEntity<List<Reto>> obtenerPorCursoIdYTipo(@PathVariable("idcurso") int idCurso,
                                                       @PathVariable("tipo") String tipo) {
        return new ResponseEntity<>(retoService.getPorCursoIdYTipo(idCurso,tipo), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> guardar(@RequestBody Reto reto) {

        if(retoService.save(reto)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody Reto reto){

        if(retoService.actualizar(id, reto)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
       if(retoService.eliminar(id)){
           return new ResponseEntity<>(HttpStatus.OK);
        }else{
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

}
