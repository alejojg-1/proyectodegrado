package co.proyectoGrado.proyectoGrado.domain.repository;

import co.proyectoGrado.proyectoGrado.domain.model.CursoContenido;


import java.util.List;

public interface CursoContenidoRepository {
    List<CursoContenido> getAll();
    CursoContenido getidCurso(int idCursoContenido);
    CursoContenido getidCategoria(int idCategoriaContenido);
    CursoContenido getCursosiId(int cursosIdCursos);
    boolean save(CursoContenido cursoContenido);
    boolean actualizar(int id, CursoContenido cursoContenido);
    boolean delete(int id);
}
