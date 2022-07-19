package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;


import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudianteCrud extends CrudRepository<EstudianteEntity, Integer> {
    List<EstudianteEntity> findByIdEstudiantes(int idEstudiante);
    List<EstudianteEntity> findByIdEstudiantesIn(List<Integer> idsEstudiante);
    EstudianteEntity findFirstByIdEstudiantes(int idEstudiante);
    EstudianteEntity findFirstByIdentificacion(int identificacion);
    EstudianteEntity findFirstByCorreo(String correo);


}
