package co.proyectoGrado.repository.persistence.crud;


import co.proyectoGrado.repository.persistence.entity.CursoDocenteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoDocenteCrud extends CrudRepository<CursoDocenteEntity, Integer> {

    CursoDocenteEntity findById_IdCursoDocente(int idCursoDocente);
    CursoDocenteEntity findFirstById_IdCursoDocente(int idDocente);
    CursoDocenteEntity findFirstByCurso_IdCursos(int idCurso);
    List<CursoDocenteEntity> findById_IdDocentes(int idDocente);
}
