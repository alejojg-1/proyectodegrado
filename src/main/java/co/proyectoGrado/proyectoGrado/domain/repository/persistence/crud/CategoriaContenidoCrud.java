package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;


import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CategoriaContenidoEntity;
import org.springframework.data.repository.CrudRepository;


public interface CategoriaContenidoCrud extends CrudRepository<CategoriaContenidoEntity, Integer> {



     CategoriaContenidoEntity findByIdCategoriaContenido(int idCategoriaContenido);
     CategoriaContenidoEntity findFirstByIdCategoriaContenido(int idCategoriaContenido);
     CategoriaContenidoEntity findByPregunta_IdPregunta(int cursosIdCursos);

}

