package co.proyectoGrado.proyectoGrado.domain.repository;

import co.proyectoGrado.proyectoGrado.domain.model.Docente;

import java.util.List;

public interface DocenteRepository {
    List<Docente> getAll();
    Docente get(int identificacion);
    Docente get(String email);
    boolean save(Docente docente);
    void delete(int idDocente);
}
