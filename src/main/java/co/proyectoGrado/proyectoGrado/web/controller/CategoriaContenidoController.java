package co.proyectoGrado.proyectoGrado.web.controller;

import co.proyectoGrado.proyectoGrado.domain.model.CategoriaContenido;
import co.proyectoGrado.proyectoGrado.domain.service.CategoriaContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contentcategory")
public class CategoriaContenidoController {
    private final CategoriaContenidoService categoriaContenidoService;

    @Autowired
    public CategoriaContenidoController(CategoriaContenidoService categoriaContenidoService) {
        this.categoriaContenidoService = categoriaContenidoService;
    }

    @GetMapping("/idCategoria/{idCategoria}")
    public ResponseEntity<CategoriaContenido> getById(@PathVariable("idCategoria") String idCategoria) {
        return new ResponseEntity<>(categoriaContenidoService.get(Integer.parseInt(idCategoria)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Boolean> save(@RequestBody CategoriaContenido docente) {
        return new ResponseEntity<>(categoriaContenidoService.save(docente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> actualizar(@PathVariable("id") int id, @RequestBody CategoriaContenido categoriaContenido){
        return new ResponseEntity<>(categoriaContenidoService.actualizar(id, categoriaContenido), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@PathVariable int id){
        return new ResponseEntity<>(categoriaContenidoService.eliminar(id), HttpStatus.OK);
    }

}
