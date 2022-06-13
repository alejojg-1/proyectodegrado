package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoCrud extends CrudRepository<CursoEntity, Integer> {
    List<CursoEntity> findByIdCursos(int idCurso);
    List<CursoEntity> findByIdCursosIn(List<Integer> idsCursos);
    CursoEntity findFirstByIdCursos(int idCurso);
    CursoEntity findFirstByGrado(String grado);
    CursoEntity findFirstByNombre(String nombre);
}
