package co.proyectoGrado.proyectoGrado.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.model.CursoContenido;
import co.proyectoGrado.proyectoGrado.persistence.entity.CategoriaContenidoEntity;
import co.proyectoGrado.proyectoGrado.persistence.entity.DocenteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaContenidoCrud extends CrudRepository<CategoriaContenidoEntity, Integer> {


    CategoriaContenidoEntity findFirstByIdCategoriaContenido(int idCategoriaContenido);

    CategoriaContenidoEntity findFirstByPregunta_IdPregunta(int cursosIdCursos);

}
