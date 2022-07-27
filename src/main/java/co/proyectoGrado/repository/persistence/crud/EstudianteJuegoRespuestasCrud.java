package co.proyectoGrado.repository.persistence.crud;

import co.proyectoGrado.repository.persistence.entity.EstudianteJuegoRespuestasEntity;
import org.springframework.data.repository.CrudRepository;

public interface EstudianteJuegoRespuestasCrud extends CrudRepository<EstudianteJuegoRespuestasEntity, Integer> {

    EstudianteJuegoRespuestasEntity findFirstByIdEstudianteJuegoRespuestas(int idEstudianteJuegoRespuestas);
    EstudianteJuegoRespuestasEntity findByIdpreguntas(int idPregunta);
    EstudianteJuegoRespuestasEntity findFirsByIdJuegoPregunta(Integer idEstudianteJuego);
}
