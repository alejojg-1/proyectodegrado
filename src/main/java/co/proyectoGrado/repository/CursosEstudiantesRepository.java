package co.proyectoGrado.repository;

import co.proyectoGrado.domain.model.CursoEstudiante;

import java.util.List;

public interface CursosEstudiantesRepository {

    List<CursoEstudiante> getAll();
    CursoEstudiante getIdEstudiante(int idEstudiantes);
    List<CursoEstudiante> getByIdEstudiante(int idEstudiante);
    List<CursoEstudiante> getIdCursos(int idCursos);
    boolean save(CursoEstudiante cursoEstudiante);
    Boolean actualizar(int id, CursoEstudiante estudiante);
    Boolean delete(int id);

}
