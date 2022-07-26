package co.proyectoGrado.repository;

import co.proyectoGrado.domain.model.Estudiante;

import java.util.List;

public interface EstudianteRepository {
    List<Estudiante> getAll();
    List<Estudiante> getByIds(List<Integer> listaIds);
    Estudiante get(int identificacion);
    Estudiante getById(int idEstudiante);
    Estudiante get(String email);
    boolean save(Estudiante estudiante);
    Boolean actualizar(Estudiante estudiante);
    boolean delete(int idEstudiante);
}
