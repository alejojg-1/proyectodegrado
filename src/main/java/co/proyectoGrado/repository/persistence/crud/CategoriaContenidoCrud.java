package co.proyectoGrado.repository.persistence.crud;


import co.proyectoGrado.repository.persistence.entity.CategoriaContenidoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoriaContenidoCrud extends CrudRepository<CategoriaContenidoEntity, Integer> {

    CategoriaContenidoEntity findByIdCategoriaContenido(int idCategoriaContenido);
    CategoriaContenidoEntity findFirstByIdCategoriaContenido(int idCategoriaContenido);
    CategoriaContenidoEntity findFirstByNombre(String nombre);
    CategoriaContenidoEntity findFirstByIdPreguntas(int idPreguntas);
    List<CategoriaContenidoEntity> findByIdCategoriaContenidoIn(List<Integer> idsCategoriaContenido);

}

