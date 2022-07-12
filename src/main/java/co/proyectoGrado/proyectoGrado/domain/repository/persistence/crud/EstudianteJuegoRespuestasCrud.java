package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteJuegoRespuestasEntity;
import org.springframework.data.repository.CrudRepository;

public interface EstudianteJuegoRespuestasCrud extends CrudRepository<EstudianteJuegoRespuestasEntity, Integer> {

    EstudianteJuegoRespuestasEntity findFirstByIdEstudianteJuegoRespuestas(int idEstudianteJuegoRespuestas);
    EstudianteJuegoRespuestasEntity findByIdpreguntas(int idPregunta);
    //EstudianteJuegoRespuestasEntity findByJuegoPregunta_IdJuegoPreguntas(int idJuegoPregunta);


}
