package co.proyectoGrado.proyectoGrado.domain.service.cursocontenido;


import co.proyectoGrado.proyectoGrado.domain.model.CursoContenido;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoContenidoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoContenidoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoContenidoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoContenidoService {

    private final CursoContenidoRepository cursoContenidoRepository;

    @Autowired
    private CursoContenidoCrud cursoContenidoCrud;

    @Autowired
    public CursoContenidoService(CursoContenidoRepository cursoContenidoRepository) {
        this.cursoContenidoRepository = cursoContenidoRepository;
    }

    private final ModelMapper mapper = new ModelMapper();

    public List<CursoContenido> getAll() {
        return cursoContenidoRepository.getAll();
    }

    public List <CursoContenido> obtenerContenidoPorIdCategoria(int idCategoriaContenido, int IdCurso) {
        return cursoContenidoRepository.getByIdCategoriaYIdCurso(idCategoriaContenido,IdCurso);
    }

    public boolean save(CursoContenido cursoContenido) {

        try {
            cursoContenidoRepository.save(cursoContenido);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(int id, CursoContenido cursoContenido) {
        CursoContenidoEntity contenido = cursoContenidoCrud.findFirstById_IdCursoContenido(id);
        if ("".equals(contenido.getId().getIdCursoContenido())) {
            CursoContenidoEntity contenidoMapper = mapper.map(cursoContenido, CursoContenidoEntity.class);
            contenidoMapper.getId().setIdCursoContenido(contenido.getId().getIdCursoContenido());
            cursoContenidoCrud.save(contenidoMapper);
        }
        return cursoContenidoRepository.actualizar(id, cursoContenido);
    }

    public ResponseEntity<Object> eliminar(int id) {
        cursoContenidoRepository.delete(id);
        return ResponseEntity.ok().body("Eliminacion exitosa");
    }

}
