package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteJuegoRespuestasEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudianteJuegoRespuestasCrud extends CrudRepository<EstudianteJuegoRespuestasEntity, Integer> {

    EstudianteJuegoRespuestasEntity findFirstByIdEstudianteJuegoRespuestas(int idEstudianteJuegoRespuestas);
    EstudianteJuegoRespuestasEntity findByIdpreguntas(int idPregunta);
    List<EstudianteJuegoRespuestasEntity> findByIdEstudianteJuego(Integer idEstudianteJuego);
}
