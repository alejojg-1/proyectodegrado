package co.proyectoGrado.proyectoGrado.domain.repository;


import co.proyectoGrado.proyectoGrado.domain.model.Pregunta;


import java.util.List;

public interface PreguntaRepository {
    List<Pregunta> getAll();
    List<Pregunta> getByIds(List<Integer> idsPreguntas);
    Pregunta get(int idpregunta);
    boolean save(Pregunta pregunta);
    boolean actualizar(int id, Pregunta pregunta);
    Boolean delete(int idpregunta);
}
