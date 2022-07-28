package co.proyectoGrado.domain.service.cursocontenido;


import co.proyectoGrado.domain.dto.DtoRespuesta;
import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.model.CursoContenido;
import co.proyectoGrado.repository.CursoContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoContenidoService {

    private final CursoContenidoRepository cursoContenidoRepository;

    @Autowired
    public CursoContenidoService(CursoContenidoRepository cursoContenidoRepository) {
        this.cursoContenidoRepository = cursoContenidoRepository;
    }

    public List<CursoContenido> getAll() {
        return cursoContenidoRepository.getAll();
    }

    public List<CursoContenido> obtenerContenidoPorIdCategoria(int idCategoriaContenido, int IdCurso) {
        return cursoContenidoRepository.getByIdCategoriaYIdCurso(idCategoriaContenido, IdCurso);
    }

    public boolean save(CursoContenido cursoContenido) {

        try {
            cursoContenidoRepository.save(cursoContenido);
            return Boolean.TRUE;
        } catch (ExcepcionDeProceso e) {
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
