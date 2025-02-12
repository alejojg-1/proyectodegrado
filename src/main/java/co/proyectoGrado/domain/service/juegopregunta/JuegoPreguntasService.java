package co.proyectoGrado.domain.service.juegopregunta;


import co.proyectoGrado.domain.model.JuegoPregunta;

import co.proyectoGrado.repository.JuegoPreguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoPreguntasService {

    private final JuegoPreguntasRepository juegoPreguntasRepository;

    @Autowired
    public JuegoPreguntasService(JuegoPreguntasRepository juegoPreguntasRepository) {
        this.juegoPreguntasRepository = juegoPreguntasRepository;
    }

    public List<JuegoPregunta> getAll(){
        return juegoPreguntasRepository.getAll();
    }

    public JuegoPregunta obtenerPorIdRetoYIdPregunta(int idReto, int idPregunta) {
        return juegoPreguntasRepository.obtenerPorIdRetoYIdPregunta(idReto,idPregunta);
    }

    public  JuegoPregunta get(int idpreguntas) {

        return juegoPreguntasRepository.get(idpreguntas);
    }

    public boolean save( List<JuegoPregunta> listaJuegoPreguntas) {

        try {
            juegoPreguntasRepository.save(listaJuegoPreguntas);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public  Boolean actualizar(int id, JuegoPregunta juegoPregunta) {
        return  juegoPreguntasRepository.actualizar(id, juegoPregunta);
    }

    public Boolean eliminar(int id) {
        return juegoPreguntasRepository.delete(id);
    }
}
