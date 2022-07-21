package co.proyectoGrado.proyectoGrado.domain.service.cursocontenido;


import co.proyectoGrado.proyectoGrado.domain.dto.DtoRespuesta;
import co.proyectoGrado.proyectoGrado.domain.model.CursoContenido;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoContenidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoContenidoService {

    private final CursoContenidoRepository cursoContenidoRepository;

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

    public Boolean actualizar(CursoContenido cursoContenido) {
        return cursoContenidoRepository.actualizar(cursoContenido);
    }

    public DtoRespuesta eliminar(int id) {
        cursoContenidoRepository.delete(id);
        return new DtoRespuesta("Eliminacion exitosa");
    }
}
