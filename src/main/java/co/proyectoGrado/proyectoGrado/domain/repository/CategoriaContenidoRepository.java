package co.proyectoGrado.proyectoGrado.domain.repository;

import co.proyectoGrado.proyectoGrado.domain.model.CategoriaContenido;
import co.proyectoGrado.proyectoGrado.domain.model.Docente;

import java.util.List;

public interface CategoriaContenidoRepository {
    List<CategoriaContenido> getAll();
    CategoriaContenido get(int idCategoriaContenido);
    CategoriaContenido getPregunta(int idPregunta);

    boolean save(CategoriaContenido categoriaContenido);

    Boolean actualizar(int id, CategoriaContenido categoriaContenido);
    Boolean delete(int id);
}
