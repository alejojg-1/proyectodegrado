package co.proyectoGrado.proyectoGrado.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.Pregunta;
import co.proyectoGrado.proyectoGrado.domain.repository.PreguntaRepository;
import co.proyectoGrado.proyectoGrado.persistence.crud.PreguntaCrud;
import co.proyectoGrado.proyectoGrado.persistence.entity.PreguntaEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PreguntaRepositoryImpl implements PreguntaRepository {

    private final PreguntaCrud preguntaCrud;

    @Autowired
    public PreguntaRepositoryImpl(PreguntaCrud preguntaCrud) {
        this.preguntaCrud = preguntaCrud;
    }

    @Override
    public List<Pregunta> getAll() {
        List<Pregunta> preguntas = new ArrayList<>();

        preguntaCrud.findAll().forEach(preguntaEntity -> {
            Pregunta pregunta = new Pregunta(preguntaEntity.getIdPregunta(), preguntaEntity.getTexto(),
                    preguntaEntity.getImagen(), preguntaEntity.getRespuesta(), preguntaEntity.getOpcion1(),
                    preguntaEntity.getOpcion2(), preguntaEntity.getOpcion3(), preguntaEntity.getOpcion4(),
                    "S".equals(preguntaEntity.getEstado()));
        });

        return preguntas;
    }

    @Override
    public Pregunta get(int idpregunta) {
        PreguntaEntity preguntaEntity = (PreguntaEntity) preguntaCrud.findByIdPregunta(idpregunta);
        if (preguntaEntity != null) {
            return new Pregunta(preguntaEntity.getIdPregunta(), preguntaEntity.getTexto(),
                    preguntaEntity.getImagen(), preguntaEntity.getRespuesta(), preguntaEntity.getOpcion1(),
                    preguntaEntity.getOpcion2(), preguntaEntity.getOpcion3(), preguntaEntity.getOpcion4(),
                    "S".equals(preguntaEntity.getEstado()));
        } else {
            return null;
        }
    }

    @Override
    public boolean save(Pregunta pregunta) {
        try {
            PreguntaEntity preguntaEntity = new PreguntaEntity();
            preguntaEntity.setIdPregunta(pregunta.getIdPregunta());
            preguntaEntity.setTexto(pregunta.getTexto());
            preguntaEntity.setImagen(pregunta.getImagen());
            preguntaEntity.setRespuesta(pregunta.getRespuesta());
            preguntaEntity.setOpcion1(pregunta.getOpcion1());
            preguntaEntity.setOpcion2(pregunta.getOpcion2());
            preguntaEntity.setOpcion3(pregunta.getOpcion3());
            preguntaEntity.setOpcion4(pregunta.getOpcion4());
            preguntaEntity.setEstado(pregunta.isEstado() ? 'S' : 'N');

            preguntaCrud.save(preguntaEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean actualizar(int id, Pregunta pregunta) {
        if (preguntaCrud.findById(id) != null) {
            try {
                PreguntaEntity preguntaEntity = new PreguntaEntity();
                preguntaEntity.setIdPregunta(pregunta.getIdPregunta());
                preguntaEntity.setTexto(pregunta.getTexto());
                preguntaEntity.setImagen(pregunta.getImagen());
                preguntaEntity.setRespuesta(pregunta.getRespuesta());
                preguntaEntity.setOpcion1(pregunta.getOpcion1());
                preguntaEntity.setOpcion2(pregunta.getOpcion2());
                preguntaEntity.setOpcion3(pregunta.getOpcion3());
                preguntaEntity.setOpcion4(pregunta.getOpcion4());
                preguntaEntity.setEstado(pregunta.isEstado() ? 'S' : 'N');

                preguntaCrud.save(preguntaEntity);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

        @Override
        public Boolean delete ( int idPregunta){
            if (preguntaCrud.findByIdPregunta(idPregunta) != null) {
                PreguntaEntity preguntaEntity = (PreguntaEntity) preguntaCrud.findByIdPregunta(idPregunta);
                preguntaEntity.setEstado('N');
                preguntaCrud.save(preguntaEntity);
                return true;
            } else {
                return false;
            }

        }
    }



