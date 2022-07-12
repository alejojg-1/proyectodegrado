package co.proyectoGrado.proyectoGrado.domain.repository;

import co.proyectoGrado.proyectoGrado.domain.model.JuegoPregunta;
import co.proyectoGrado.proyectoGrado.domain.model.Reto;

import java.util.List;

public interface JuegoPreguntasRepository {

    List<JuegoPregunta> getAll();
    List<JuegoPregunta> getByIdReto(int idReto);
    JuegoPregunta get(int idPreguntas);
    boolean save(JuegoPregunta juegoPregunta);
    boolean actualizar(int id, JuegoPregunta juegoPregunta);
    boolean delete(int id);
}
