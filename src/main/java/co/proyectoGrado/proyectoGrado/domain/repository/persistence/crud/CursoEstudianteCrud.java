package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursosEstudiantesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoEstudianteCrud extends CrudRepository<CursosEstudiantesEntity, Integer> {
    CursosEstudiantesEntity findById_IdCursoEstudiante(int idCursoEstudiantes);
    CursosEstudiantesEntity findFirstByEstudiante_IdEstudiantes(int idEstudiantes);
    List<CursosEstudiantesEntity> findById_IdCursos(int idCursos);
    List<CursosEstudiantesEntity> findById_IdEstudiantes(int idEstudiante);
}
