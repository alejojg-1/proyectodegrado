package co.proyectoGrado.repository;

import co.proyectoGrado.domain.model.CursoContenido;

import java.util.List;

public interface CursoContenidoRepository {
    List<CursoContenido> getAll();
    CursoContenido getIdCurso(int idCursoContenido);
    List<CursoContenido> getByIdCurso(int idCurso);
    List <CursoContenido> getByIdCategoriaYIdCurso(int idCategoriaContenido,int idCurso);
    CursoContenido getCursosiId(int cursosIdCursos);
    boolean save(CursoContenido cursoContenido);
    boolean actualizar(CursoContenido cursoContenido);
    boolean delete(int id);
}
