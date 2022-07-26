package co.proyectoGrado.repository;

import co.proyectoGrado.domain.model.CursoDocente;

import java.util.List;

public interface CursoDocenteRepository {
    List<CursoDocente> getAll();
    CursoDocente get(int idCursoDocente);
    CursoDocente getIdDocente(int idDocente);
    List<CursoDocente> getByIdDocente(int idDocente);
    CursoDocente getIdCursos(int idCursos);
    Boolean save(CursoDocente cursodocente);
    Boolean actualizar(int id, CursoDocente cursodocente);
    Boolean delete(int id);
}
