package co.proyectoGrado.proyectoGrado.domain.repository;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuego;


import java.util.List;

public interface EstudianteJuegoRepository {
    List<EstudianteJuego> getAll();
    EstudianteJuego getByIdReto(int idReto);
    List<EstudianteJuego> obtenerListaPorIdReto(int idReto);
    EstudianteJuego getByIdEstudiantes(int idEstudiantes);
    EstudianteJuego save(EstudianteJuego estudianteJuego);
    Boolean actualizar(int id, EstudianteJuego estudianteJuego);
    Boolean delete(int id);
}
