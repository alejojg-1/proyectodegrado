package co.proyectoGrado.proyectoGrado.domain.service;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import co.proyectoGrado.proyectoGrado.domain.repository.EstudianteJuegoRespuestasRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteJuegoRespuestasCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteJuegoRespuestasService {

    private final EstudianteJuegoRespuestasRepository estudianteJuegoRespuestasRepository;
    @Autowired
    private EstudianteJuegoRespuestasCrud estudianteJuegoRespuestasCrud;

    @Autowired
    public EstudianteJuegoRespuestasService(EstudianteJuegoRespuestasRepository estudianteJuegoRespuestasRepository) {
        this.estudianteJuegoRespuestasRepository = estudianteJuegoRespuestasRepository;
    }

    public EstudianteJuegoRespuesta get(int idjuego_preguntas)
    {
        return estudianteJuegoRespuestasRepository.getByIdJuegoPregunta(idjuego_preguntas);
    }

    public boolean save(EstudianteJuegoRespuesta estudianteJuegoRespuestas) {
        return estudianteJuegoRespuestasRepository.save(estudianteJuegoRespuestas);
    }

    public boolean actualizar(int id, EstudianteJuegoRespuesta estudianteJuegoRespuesta) {
        return  estudianteJuegoRespuestasRepository.actualizar(id, estudianteJuegoRespuesta);
    }

    public boolean eliminar(int id) {
        return estudianteJuegoRespuestasRepository.delete(id);
    }
}
