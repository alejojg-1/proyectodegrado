package co.proyectoGrado.repository;

import co.proyectoGrado.domain.model.CategoriaContenido;

import java.util.List;

public interface CategoriaContenidoRepository {
    List<CategoriaContenido> getAll();
    List<CategoriaContenido> getByIds( List<Integer> listaIdsCategoriaContenido);
    CategoriaContenido get(int idCategoriaContenido);
    CategoriaContenido getPregunta(int idPregunta);
    CategoriaContenido save(CategoriaContenido categoriaContenido);
    Boolean actualizar(int id, CategoriaContenido categoriaContenido);
    Boolean delete(int id);
}
