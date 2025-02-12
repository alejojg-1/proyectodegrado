package co.proyectoGrado.domain.service.pregunta;

import co.proyectoGrado.domain.model.JuegoPregunta;
import co.proyectoGrado.domain.model.Pregunta;
import co.proyectoGrado.repository.JuegoPreguntasRepository;
import co.proyectoGrado.repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;
    private final JuegoPreguntasRepository juegoPreguntasRepository;

    @Autowired
    public PreguntaService(PreguntaRepository preguntaRepository,
                           JuegoPreguntasRepository juegoPreguntasRepository) {
        this.preguntaRepository = preguntaRepository;
        this.juegoPreguntasRepository = juegoPreguntasRepository;
    }

    public List<Pregunta> getAll(){
        return preguntaRepository.getAll();
    }

    public List<Pregunta> obtenerPreguntasPorIdReto(int idReto){
        List<Integer> IdsPreguntas = juegoPreguntasRepository.getByIdReto(idReto)
                .stream().map(JuegoPregunta::getIdPreguntas).collect(Collectors.toList());
        return preguntaRepository.getByIds(IdsPreguntas);
    }

    public Pregunta get(int idPreguntas) {
        return preguntaRepository.get(idPreguntas);
    }

    public  List<Pregunta> save(List<Pregunta> preguntas) {
        return preguntaRepository.save(preguntas);
    }

    public Boolean actualizar(int id, Pregunta pregunta) {
        return  preguntaRepository.actualizar(id,pregunta);
    }

    public Boolean eliminar(int id) {
        return preguntaRepository.delete(id);
    }
}
