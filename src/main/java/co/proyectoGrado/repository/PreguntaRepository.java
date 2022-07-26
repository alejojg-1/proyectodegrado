package co.proyectoGrado.repository;


import co.proyectoGrado.domain.model.Pregunta;

import java.util.List;

public interface PreguntaRepository {
    List<Pregunta> getAll();
    List<Pregunta> getByIds(List<Integer> idsPreguntas);
    Pregunta get(int idpregunta);
    List<Pregunta> save(List<Pregunta> preguntas);
    boolean actualizar(int id, Pregunta pregunta);
    Boolean delete(int idpregunta);
}
