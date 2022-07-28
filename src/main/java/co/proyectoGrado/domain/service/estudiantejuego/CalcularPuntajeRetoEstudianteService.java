package co.proyectoGrado.domain.service.estudiantejuego;

import co.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import co.proyectoGrado.domain.model.Pregunta;
import co.proyectoGrado.repository.EstudianteJuegoRespuestasRepository;
import co.proyectoGrado.repository.JuegoPreguntasRepository;
import co.proyectoGrado.repository.PreguntaRepository;
import co.proyectoGrado.domain.service.pregunta.PreguntaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcularPuntajeRetoEstudianteService {

    private static final Double CALIFICACION_TOPE = 5.0D;

    private final EstudianteJuegoRespuestasRepository estudianteJuegoRespuestasRepository;
    private final PreguntaService preguntaService;
    private final JuegoPreguntasRepository juegoPreguntasRepository;
    private final PreguntaRepository preguntaRepository;

    public CalcularPuntajeRetoEstudianteService(EstudianteJuegoRespuestasRepository estudianteJuegoRespuestasRepository,
                                                JuegoPreguntasRepository juegoPreguntasRepository,
                                                PreguntaRepository preguntaRepository,
                                                PreguntaService preguntaService) {
        this.estudianteJuegoRespuestasRepository = estudianteJuegoRespuestasRepository;
        this.juegoPreguntasRepository = juegoPreguntasRepository;
        this.preguntaService = preguntaService;
        this.preguntaRepository = preguntaRepository;
    }

    public EstudianteJuego ejecutar(List<EstudianteJuegoRespuesta> listaRespuestasEstudiante, int idReto, int idEstudiante) {
        List<Pregunta> listaPreguntas = preguntaService.obtenerPreguntasPorIdReto(idReto);
        List<Pregunta> preguntasContestadasCorrectamente = obtenerRespuestasCorrectas(listaPreguntas, listaRespuestasEstudiante);
        Double puntuacion = cacularPuntuacion(listaPreguntas, preguntasContestadasCorrectamente);
        return new EstudianteJuego(puntuacion,idReto,idEstudiante);
    }

    private List<Pregunta> obtenerRespuestasCorrectas(List<Pregunta> listaPreguntas, List<EstudianteJuegoRespuesta> listaRespuestas) {
        List<Pregunta> preguntasContestadasCorrectamente = new ArrayList<>();
        listaRespuestas.forEach(respuesta -> {
            Pregunta preguntaContestada = listaPreguntas.stream().filter(pregunta -> pregunta.getIdPregunta() == respuesta.getIdPreguntas()
                            && pregunta.getRespuesta().equals(respuesta.getRespuesta()))
                    .findFirst().orElse(null);
            if (preguntaContestada != null) {
                preguntasContestadasCorrectamente.add(preguntaContestada);
            }
        });
        return preguntasContestadasCorrectamente;
    }


    private Double cacularPuntuacion(List<Pregunta> listaPreguntas, List<Pregunta> preguntasContestadasCorrectamente) {
        return (CALIFICACION_TOPE / Double.valueOf(listaPreguntas.size()))
                * (Double.valueOf(preguntasContestadasCorrectamente.size()));
    }

}
