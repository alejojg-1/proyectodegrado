package co.proyectoGrado.repository.persistence.crud;

import co.proyectoGrado.repository.persistence.entity.CursoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoCrud extends CrudRepository<CursoEntity, Integer> {
    List<CursoEntity> findByIdCursos(int idCurso);
    List<CursoEntity> findByIdCursosIn(List<Integer> idsCursos);
    CursoEntity findFirstByIdCursos(int idCurso);
    CursoEntity findFirstByGrado(String grado);
    CursoEntity findFirstByCodigo(String codigo);
    CursoEntity findFirstByNombre(String nombre);
}
