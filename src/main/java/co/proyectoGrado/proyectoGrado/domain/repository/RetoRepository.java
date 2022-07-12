package co.proyectoGrado.proyectoGrado.domain.repository;


import co.proyectoGrado.proyectoGrado.domain.model.Reto;

import java.util.List;

public interface RetoRepository {

    List<Reto> getAll();
    List<Reto> getByIdCurso(int idCurso);
    List<Reto> getPorIdCursoYTipo(int idCurso, String tipo);
    Reto get(String tipo);
    Reto getTitulo(String titulo);
    Boolean save(Reto reto);
    Boolean actualizar(int id, Reto reto);
    Boolean delete(int id);
}
