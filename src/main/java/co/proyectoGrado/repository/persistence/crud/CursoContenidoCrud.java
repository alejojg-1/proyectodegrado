package co.proyectoGrado.repository.persistence.crud;

import co.proyectoGrado.repository.persistence.entity.CursoContenidoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CursoContenidoCrud extends CrudRepository<CursoContenidoEntity, Integer> {

    CursoContenidoEntity findFirstById_IdCursoContenido(int idCursoContenido);
    CursoContenidoEntity findByCategoriaContenido_IdCategoriaContenido(int idCategoriaContenido);
    CursoContenidoEntity findByCurso_IdCursos(int idCursos);
    List<CursoContenidoEntity> findById_IdCursos(int idCurso);
    List<CursoContenidoEntity> findById_IdCategoriaContenidoAndId_IdCursos(int idCategoriaContenido, int idCurso);

}
