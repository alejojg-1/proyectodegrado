package co.proyectoGrado.domain.service.estudiantejuego;

import co.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import co.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.domain.service.estudiante.EstudianteService;
import co.proyectoGrado.domain.service.juegopregunta.JuegoPreguntasService;
import co.proyectoGrado.domain.service.estudiantejuegorespuesta.EstudianteJuegoRespuestasService;
import co.proyectoGrado.domain.service.reto.RetoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccionesCrearEstudianteJuegoSegunEscenarioService {

    private static final String ERROR_TIPO_JUEGO =  "Error el tipo reto no es un tipo definido por favor validar el reto con tipo: %s";
    private static final String TIPO_JUEGO = "J";
    private static final String TIPO_RETO = "R";
    private static final int INICIAL = 0;


    private final CalcularPuntajeRetoEstudianteService calcularPuntajeRetoEstudianteService;
    private final EstudianteJuegoRespuestasService estudianteJuegoRespuestasService;
    private final EstudianteJuegoService estudianteJuegoService;
    private final RetoService retoService;
    private final EstudianteService estudianteService;
    private final JuegoPreguntasService juegoPreguntasService;

    public AccionesCrearEstudianteJuegoSegunEscenarioService(CalcularPuntajeRetoEstudianteService calcularPuntajeRetoEstudianteService,
                                                             EstudianteJuegoRespuestasService estudianteJuegoRespuestasService,
                                                             EstudianteJuegoService estudianteJuegoService, RetoService retoService,
                                                             EstudianteService estudianteService,
                                                             JuegoPreguntasService juegoPreguntasService) {
        this.calcularPuntajeRetoEstudianteService = calcularPuntajeRetoEstudianteService;
        this.estudianteJuegoRespuestasService = estudianteJuegoRespuestasService;
        this.estudianteJuegoService = estudianteJuegoService;
        this.retoService = retoService;
        this.estudianteService = estudianteService;
        this.juegoPreguntasService = juegoPreguntasService;
    }

    public Double ejecutar (List<EstudianteJuegoRespuesta> listaRespuestasEstudiante, String correoEstudiante){
        return accionesSegunEscenario(listaRespuestasEstudiante,retoService.getById(listaRespuestasEstudiante.get(INICIAL).getIdReto()),
                estudianteService.get(correoEstudiante).getIdEstudiante());
    }

    private Double accionesSegunEscenario(List<EstudianteJuegoRespuesta> listaRespuestasEstudiante,
                                          Reto reto, int idEstudiante){
        EstudianteJuego estudianteJuegoCalculado =  puntajeEstudianteReto(listaRespuestasEstudiante,
                reto.getIdReto(),idEstudiante);
        if(TIPO_JUEGO.equals(reto.getTipo())){
            return estudianteJuegoCalculado.getCalificacion();
        }
        else if(TIPO_RETO.equals(reto.getTipo())){
            EstudianteJuego estudianteJuegoCreado =estudianteJuegoService.save(estudianteJuegoCalculado);
            listaRespuestasEstudiante.forEach(estudianteJuegorespuestaACrear ->{
                estudianteJuegorespuestaACrear.setIdjuegoPreguntas(juegoPreguntasService.obtenerPorIdRetoYIdPregunta(estudianteJuegorespuestaACrear.getIdReto(),
                        estudianteJuegorespuestaACrear.getIdPreguntas()).getIdJuegoPreguntas());
                estudianteJuegorespuestaACrear.setIdEstudianteJuego(estudianteJuegoCreado.getIdEstudianteJuego());
            });
            //estudianteJuegoRespuestasService.save(listaRespuestasEstudiante);
            return estudianteJuegoCalculado.getCalificacion();
        }
        else{
            throw new RuntimeException(String.format(ERROR_TIPO_JUEGO,reto.getTipo()));
        }
    }

    private EstudianteJuego puntajeEstudianteReto(List<EstudianteJuegoRespuesta> listaRespuestasEstudiante,
                                                  int idReto,int idEstudiante){
        return calcularPuntajeRetoEstudianteService.ejecutar(listaRespuestasEstudiante,idReto,idEstudiante);
    }

}
