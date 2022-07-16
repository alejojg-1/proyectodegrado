package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.PreguntaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PreguntaCrud extends CrudRepository<PreguntaEntity, Integer> {
  List<PreguntaEntity> findByIdPregunta(int idPregunta);
  PreguntaEntity findFirstByIdPregunta(int idPregunta);
  List<PreguntaEntity> findByIdPreguntaIn(List<Integer> idsPreguntas);


}
