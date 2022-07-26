package co.proyectoGrado.repository;

import co.proyectoGrado.domain.model.Curso;


import java.util.List;

public interface CursoRepository {

    List<Curso> getAll();
    List<Curso> getByIdsCursos(List<Integer> listaIdsCursos);
    Curso getById(int idCurso);
    Curso getByCodigo(String codigo);
    Curso getByGrado(String grado);
    Curso getByNombre(String nombre);
    Curso save(Curso curso);
    Boolean actualizar(int id, Curso curso);
    Boolean delete(int idCurso);
}
