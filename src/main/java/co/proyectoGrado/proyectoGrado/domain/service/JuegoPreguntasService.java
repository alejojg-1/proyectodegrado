package co.proyectoGrado.proyectoGrado.domain.service;


import co.proyectoGrado.proyectoGrado.domain.model.JuegoPregunta;

import co.proyectoGrado.proyectoGrado.domain.repository.JuegoPreguntasRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.JuegoPreguntasCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.JuegoPreguntasEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuegoPreguntasService {

    private final JuegoPreguntasRepository juegoPreguntasRepository;
    @Autowired
    private JuegoPreguntasCrud juegoPreguntasCrud;


    @Autowired
    public JuegoPreguntasService(JuegoPreguntasRepository juegoPreguntasRepository) {
        this.juegoPreguntasRepository = juegoPreguntasRepository;
    }
    private final ModelMapper mapper = new ModelMapper();
    public List<JuegoPregunta> getAll(){
        return juegoPreguntasRepository.getAll();
    }

    public  JuegoPregunta get(int idpreguntas) {

        return juegoPreguntasRepository.get(idpreguntas);
    }

    public boolean save(JuegoPregunta juegoPreguntas) {

        try {
            juegoPreguntasRepository.save(juegoPreguntas);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public static Boolean actualizar(int id, JuegoPregunta juegoPregunta) {
        return  juegoPreguntasRepository.actualizar(id, juegoPregunta);
    }

    public Boolean eliminar(int id) {
        return juegoPreguntasRepository.delete(id);
    }
}
