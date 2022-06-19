package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;


import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CategoriaContenidoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoriaContenidoCrud extends CrudRepository<CategoriaContenidoEntity, Integer> {

    CategoriaContenidoEntity findByIdCategoriaContenido(int idCategoriaContenido);

    CategoriaContenidoEntity findFirstByIdCategoriaContenido(int idCategoriaContenido);

    CategoriaContenidoEntity findByPregunta_IdPregunta(int cursosIdCursos);

    List<CategoriaContenidoEntity> findByIdCategoriaContenidoIn(List<Integer> idsCategoriaContenido);

}

