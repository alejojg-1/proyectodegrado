package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.JuegoPreguntasEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JuegoPreguntasCrud extends CrudRepository<JuegoPreguntasEntity, Integer> {

    JuegoPreguntasEntity findFirstById_IdJuegoPreguntas(int idJuegoPreguntas) ;
    JuegoPreguntasEntity findByPregunta_IdPregunta(int idPreguntas);
    List<JuegoPreguntasEntity> findById_IdReto(int idReto);

}
