package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursosEstudiantesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoEstudianteCrud extends CrudRepository<CursosEstudiantesEntity, Integer> {
    CursosEstudiantesEntity findByIdCursoEstudiante(int idCursoEstudiantes);
    CursosEstudiantesEntity findFirstByEstudiante_IdEstudiantes(int idEstudiantes);
    CursosEstudiantesEntity findFirstByCurso_IdCursos(int idCursos);
    List<CursosEstudiantesEntity> findById_IdEstudiantes(int idEstudiante);
}
