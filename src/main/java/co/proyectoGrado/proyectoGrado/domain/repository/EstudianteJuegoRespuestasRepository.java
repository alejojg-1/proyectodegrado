package co.proyectoGrado.proyectoGrado.domain.repository;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuegoRespuesta;

import java.util.List;

public interface EstudianteJuegoRespuestasRepository {
    List<EstudianteJuegoRespuesta> getAll();
    EstudianteJuegoRespuesta getByIdJuegoPregunta(int idJuegoPregunta);
    EstudianteJuegoRespuesta getIdPreguntas(int idPreguntas);
    boolean save(List<EstudianteJuegoRespuesta> listaEstudianteJuegoRespuesta);
    boolean actualizar(int id, EstudianteJuegoRespuesta estudianteJuegoRespuesta);
    boolean delete(int id);

}
