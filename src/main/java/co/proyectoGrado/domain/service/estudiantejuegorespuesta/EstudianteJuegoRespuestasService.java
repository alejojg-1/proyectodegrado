package co.proyectoGrado.domain.service.estudiantejuegorespuesta;

import co.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import co.proyectoGrado.repository.EstudianteJuegoRespuestasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteJuegoRespuestasService {

    private final EstudianteJuegoRespuestasRepository estudianteJuegoRespuestasRepository;

    @Autowired
    public EstudianteJuegoRespuestasService(EstudianteJuegoRespuestasRepository estudianteJuegoRespuestasRepository) {
        this.estudianteJuegoRespuestasRepository = estudianteJuegoRespuestasRepository;
    }

    public EstudianteJuegoRespuesta get(int idjuego_preguntas)
    {
        return estudianteJuegoRespuestasRepository.getByIdJuegoPregunta(idjuego_preguntas);
    }

    public boolean save(List<EstudianteJuegoRespuesta> listaEstudianteJuegoRespuestas) {
        return estudianteJuegoRespuestasRepository.save(listaEstudianteJuegoRespuestas);
    }

    public boolean actualizar(int id, EstudianteJuegoRespuesta estudianteJuegoRespuesta) {
        return  estudianteJuegoRespuestasRepository.actualizar(id, estudianteJuegoRespuesta);
    }

    public boolean eliminar(int id) {
        return estudianteJuegoRespuestasRepository.delete(id);
    }
}
