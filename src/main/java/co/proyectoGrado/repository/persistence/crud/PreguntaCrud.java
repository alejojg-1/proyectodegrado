package co.proyectoGrado.repository.persistence.crud;

import co.proyectoGrado.repository.persistence.entity.PreguntaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PreguntaCrud extends CrudRepository<PreguntaEntity, Integer> {
  List<PreguntaEntity> findByIdPregunta(int idPregunta);
  PreguntaEntity findFirstByIdPregunta(int idPregunta);
  List<PreguntaEntity> findByIdPreguntaIn(List<Integer> idsPreguntas);


}
