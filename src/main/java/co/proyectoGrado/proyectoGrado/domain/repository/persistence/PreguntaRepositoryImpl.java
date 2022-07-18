package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.Pregunta;
import co.proyectoGrado.proyectoGrado.domain.repository.PreguntaRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.PreguntaCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.PreguntaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class PreguntaRepositoryImpl implements PreguntaRepository {

    private final PreguntaCrud preguntaCrud;
    private final String ACTIVO = "t";

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
                    ACTIVO.equals(preguntaEntity.getEstado()));
            if(pregunta.isEstado()==true) {
                preguntas.add(pregunta);
            }
        });

        return preguntas;
    }

    @Override
    public List<Pregunta> getByIds(List<Integer> idsPreguntas) {
        List<Pregunta> preguntas = new ArrayList<>();

        preguntaCrud.findByIdPreguntaIn(idsPreguntas).forEach(preguntaEntity -> {
            Pregunta pregunta = new Pregunta(preguntaEntity.getIdPregunta(), preguntaEntity.getTexto(),
                    preguntaEntity.getImagen(), preguntaEntity.getRespuesta(), preguntaEntity.getOpcion1(),
                    preguntaEntity.getOpcion2(), preguntaEntity.getOpcion3(), preguntaEntity.getOpcion4(),
                    ACTIVO.equals(preguntaEntity.getEstado()));
            if(pregunta.isEstado()==true) {
                preguntas.add(pregunta);
            }
        });

        return preguntas;
    }

    @Override
    public Pregunta get(int idpregunta) {
        PreguntaEntity preguntaEntity = preguntaCrud.findFirstByIdPregunta(idpregunta);
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
    public List<Pregunta> save(List<Pregunta> preguntas) {

        try {
            List<PreguntaEntity> listaPreguntasEntity = new ArrayList<>();
            List<PreguntaEntity> preguntasCreadas = new ArrayList<>();
            preguntas.forEach(pregunta ->{
                PreguntaEntity preguntaEntity = new PreguntaEntity();
                preguntaEntity.setIdPregunta(pregunta.getIdPregunta());
                preguntaEntity.setTexto(pregunta.getTexto());
                preguntaEntity.setImagen(pregunta.getImagen());
                preguntaEntity.setRespuesta(pregunta.getRespuesta());
                preguntaEntity.setOpcion1(pregunta.getOpcion1());
                preguntaEntity.setOpcion2(pregunta.getOpcion2());
                preguntaEntity.setOpcion3(pregunta.getOpcion3());
                preguntaEntity.setOpcion4(pregunta.getOpcion4());
                preguntaEntity.setEstado(pregunta.isEstado() ? String.valueOf('t') : String.valueOf('f'));
                listaPreguntasEntity.add(preguntaEntity);
                preguntasCreadas.add(preguntaCrud.save(preguntaEntity));
            });
            return entityToDomain(preguntasCreadas);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Pregunta> entityToDomain (List<PreguntaEntity> listaPreguntasEntity){
        List<Pregunta> litaPreguntas = new ArrayList<>();

        listaPreguntasEntity.forEach(preguntaEntity ->{
            Pregunta pregunta = new Pregunta(preguntaEntity.getIdPregunta(), preguntaEntity.getTexto(),
                    preguntaEntity.getImagen(), preguntaEntity.getRespuesta(), preguntaEntity.getOpcion1(),
                    preguntaEntity.getOpcion2(), preguntaEntity.getOpcion3(), preguntaEntity.getOpcion4(),
                    ACTIVO.equals(preguntaEntity.getEstado()));
            litaPreguntas.add(pregunta);
        });

        return litaPreguntas;
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
                preguntaEntity.setEstado(pregunta.isEstado() ? String.valueOf('t') : String.valueOf('f'));

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
                PreguntaEntity preguntaEntity =  preguntaCrud.findFirstByIdPregunta(idPregunta);
                preguntaEntity.setEstado("f");
                preguntaCrud.save(preguntaEntity);
                return true;
            } else {
                return false;
            }

        }
    }



