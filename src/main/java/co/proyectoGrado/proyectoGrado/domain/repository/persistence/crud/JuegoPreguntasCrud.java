package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.JuegoPreguntasEntity;
import org.springframework.data.repository.CrudRepository;

public interface JuegoPreguntasCrud extends CrudRepository<JuegoPreguntasEntity, Integer> {

    JuegoPreguntasEntity findFirstByIdJuegoPreguntas(int idJuegoPreguntas) ;
    JuegoPreguntasEntity findByPregunta_IdPregunta(int idPreguntas);
    JuegoPreguntasEntity findByReto_IdReto(int idReto);

}
