package co.proyectoGrado.web.controller;

import co.proyectoGrado.domain.model.CategoriaContenido;
import co.proyectoGrado.domain.service.categoriacontenido.CategoriaContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categoria-contenido")
public class CategoriaContenidoController {
    private final CategoriaContenidoService categoriaContenidoService;

    @Autowired
    public CategoriaContenidoController(CategoriaContenidoService categoriaContenidoService) {
        this.categoriaContenidoService = categoriaContenidoService;
    }
    @GetMapping()
    public ResponseEntity <List<CategoriaContenido>> getAll() {
        return new ResponseEntity<>(categoriaContenidoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/curso/{idCurso}")
    public ResponseEntity <List<CategoriaContenido>> getByIdCurso(@PathVariable("idCurso")int idCurso) {
        return new ResponseEntity<>(categoriaContenidoService.obtenerCategoriaPorCursoId(idCurso), HttpStatus.OK);
    }

    @GetMapping("/idCategoria/{idCategoria}")
    public ResponseEntity<CategoriaContenido> getById(@PathVariable("idCategoria") String idCategoria) {
        return new ResponseEntity<>(categoriaContenidoService.get(Integer.parseInt(idCategoria)), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<CategoriaContenido> save(@RequestBody CategoriaContenido categoriaContenido) {
         return new ResponseEntity<>(categoriaContenidoService.save(categoriaContenido), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody CategoriaContenido categoriaContenido){
        if(categoriaContenidoService.actualizar(id, categoriaContenido)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        if(categoriaContenidoService.eliminar(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
