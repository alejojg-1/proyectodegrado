package co.proyectoGrado.repository;


import co.proyectoGrado.domain.model.Reto;

import java.util.List;

public interface RetoRepository {

    List<Reto> getAll();
    List<Reto> getByIdCurso(int idCurso);
    List<Reto> getPorIdCursoYTipo(int idCurso, String tipo);
    Reto get(String tipo);
    Reto getById(Integer idReto);
    Reto getTitulo(String titulo);
    Reto save(Reto reto);
    Boolean actualizar(int id, Reto reto);
    Boolean delete(int id);
}
