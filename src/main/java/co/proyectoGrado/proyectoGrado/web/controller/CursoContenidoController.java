package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.dto.DtoRespuesta;
import co.proyectoGrado.proyectoGrado.domain.model.CursoContenido;
import co.proyectoGrado.proyectoGrado.domain.service.cursocontenido.CursoContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso-contenido")
public class CursoContenidoController {

    private final CursoContenidoService cursoContenidoService;

    @Autowired
    public CursoContenidoController(CursoContenidoService cursoContenidoService) {
        this.cursoContenidoService = cursoContenidoService;
    }

    @GetMapping()
    public ResponseEntity<List<CursoContenido>> getAll() {
        return new ResponseEntity<>(cursoContenidoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/categoria-contenido/{idcategoriacontenido}/curso/{idcurso}")
    public ResponseEntity<List<CursoContenido>> obtenerContenidoPorIdCategoriaYIdCurso(@PathVariable("idcategoriacontenido") int idCategoriaContenido,
                                                                                       @PathVariable("idcurso") int idCurso) {
        return new ResponseEntity<>(cursoContenidoService.obtenerContenidoPorIdCategoria(idCategoriaContenido, idCurso), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody CursoContenido cursoContenido) {
        if (cursoContenidoService.save(cursoContenido)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Boolean> actualizar(@RequestBody CursoContenido cursoContenido) {
        if (cursoContenidoService.actualizar(cursoContenido)) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoRespuesta> eliminar(@PathVariable int id) {
        return new ResponseEntity<>(cursoContenidoService.eliminar(id), HttpStatus.OK);
    }

}
