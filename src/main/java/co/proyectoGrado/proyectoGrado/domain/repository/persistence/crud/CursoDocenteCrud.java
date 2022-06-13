package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;


import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoDocenteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoDocenteCrud extends CrudRepository<CursoDocenteEntity, Integer> {

    CursoDocenteEntity findByIdCursoDocente(int idCursoDocente);
    CursoDocenteEntity findFirstByIdCursoDocente(int idDocente);
    CursoDocenteEntity findFirstByCurso_IdCursos(int idCurso);
    // obtener cursoDocenteEntity por id docente
    List<CursoDocenteEntity> findById_IdDocentes(int idDocente);
}
