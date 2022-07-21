package co.proyectoGrado.proyectoGrado.domain.service.reto;

import co.proyectoGrado.proyectoGrado.domain.dto.DtoCreacionReto;
import co.proyectoGrado.proyectoGrado.domain.model.JuegoPregunta;
import co.proyectoGrado.proyectoGrado.domain.model.Pregunta;
import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.service.juegopregunta.JuegoPreguntasService;
import co.proyectoGrado.proyectoGrado.domain.service.pregunta.PreguntaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreacionRetoService {

    private final RetoService retoService;
    private final PreguntaService preguntaService;
    private final JuegoPreguntasService juegoPreguntasService;
    public CreacionRetoService(RetoService retoService,
                               PreguntaService preguntaService,
                               JuegoPreguntasService juegoPreguntasService){

        this.retoService = retoService;
        this.preguntaService = preguntaService;
        this.juegoPreguntasService = juegoPreguntasService;
    }

    public Boolean ejecutar(DtoCreacionReto CreacionReto){
        Reto reto =  crearReto(CreacionReto.getReto());
        List<Pregunta> listaPreguntasCreadas = crearPreguntas(CreacionReto.getListaPreguntas());

        return juegoPreguntasService.save(crearRelacionJuegoPreguntas(reto,listaPreguntasCreadas));
    }

    private List<JuegoPregunta> crearRelacionJuegoPreguntas(Reto reto, List<Pregunta> listaPreguntas){
         List<JuegoPregunta> relacionJuegoPregunta = new ArrayList<>();
        listaPreguntas.forEach(pregunta ->{
            JuegoPregunta juegoPregunta = new JuegoPregunta(null,pregunta.getIdPregunta(),
                    reto.getIdReto(),true);
            relacionJuegoPregunta.add(juegoPregunta);
        });
        return relacionJuegoPregunta;
    }

    private Reto crearReto(Reto reto) {
        try {
            return retoService.save(reto);
        } catch (Exception e) {
          throw  new RuntimeException(e);
        }
    }

    private List<Pregunta> crearPreguntas(List<Pregunta> listaPreguntas) {
        try {
            return preguntaService.save(listaPreguntas);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
