package co.proyectoGrado.proyectoGrado.domain.repository;

import co.proyectoGrado.proyectoGrado.domain.model.Curso;


import java.util.List;

public interface CursoRepository {

    List<Curso> getAll();
    Curso getByGrado(String grado);
    Curso getByNombre(String nombre);
    Boolean save(Curso curso);
    Boolean actualizar(int id, Curso curso);
    Boolean delete(int idCurso);
}
