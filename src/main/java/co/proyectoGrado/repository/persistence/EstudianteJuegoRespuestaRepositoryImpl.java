package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import co.proyectoGrado.repository.EstudianteJuegoRespuestasRepository;
import co.proyectoGrado.repository.persistence.crud.EstudianteJuegoCrud;
import co.proyectoGrado.repository.persistence.crud.EstudianteJuegoRespuestasCrud;
import co.proyectoGrado.repository.persistence.crud.JuegoPreguntasCrud;
import co.proyectoGrado.repository.persistence.entity.EstudianteJuegoEntity;
import co.proyectoGrado.repository.persistence.entity.EstudianteJuegoRespuestasEntity;
import co.proyectoGrado.repository.persistence.entity.JuegoPreguntasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EstudianteJuegoRespuestaRepositoryImpl implements EstudianteJuegoRespuestasRepository {

    private final JuegoPreguntasCrud juegoPreguntasCrud;
    private final EstudianteJuegoCrud estudianteJuegoCrud;
    private final EstudianteJuegoRespuestasCrud estudianteJuegoRespuestasCrud;

    @Autowired
    public EstudianteJuegoRespuestaRepositoryImpl(JuegoPreguntasCrud juegoPreguntasCrud,
                                                  EstudianteJuegoCrud estudianteJuegoCrud,
                                                  EstudianteJuegoRespuestasCrud estudianteJuegoRespuestasCrud) {
        this.juegoPreguntasCrud = juegoPreguntasCrud;
        this.estudianteJuegoCrud = estudianteJuegoCrud;
        this.estudianteJuegoRespuestasCrud = estudianteJuegoRespuestasCrud;
    }

    @Override
    public List<EstudianteJuegoRespuesta> getAll() {
        List<EstudianteJuegoRespuesta> estudianteJuegoRespuestas = new ArrayList<>();

        estudianteJuegoRespuestasCrud.findAll().forEach(estudianteJuegoRespuestasEntity -> {
            EstudianteJuegoRespuesta estudianteJuegoRespuesta = new EstudianteJuegoRespuesta(estudianteJuegoRespuestasEntity.getIdEstudianteJuegoRespuestas()
                    ,estudianteJuegoRespuestasEntity.getIdEstudianteJuego(),estudianteJuegoRespuestasEntity.getIdJuegoPregunta(),
                    estudianteJuegoRespuestasEntity.getIdpreguntas(), estudianteJuegoRespuestasEntity.getIdReto(),
                    estudianteJuegoRespuestasEntity.getRespuesta(), "t".equals(estudianteJuegoRespuestasEntity.getEstado()));
            estudianteJuegoRespuestas.add(estudianteJuegoRespuesta);
        });
        return estudianteJuegoRespuestas;
    }

    @Override
    public EstudianteJuegoRespuesta getByIdJuegoPregunta(int idJuegoPregunta) {

        // EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = estudianteJuegoRespuestasCrud.findByJuegoPregunta_IdJuegoPreguntas(idJuegoPregunta);
        EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = new EstudianteJuegoRespuestasEntity();

        if (estudianteJuegoRespuestasEntity != null) {
            return  new EstudianteJuegoRespuesta(estudianteJuegoRespuestasEntity.getIdEstudianteJuegoRespuestas()
                    ,estudianteJuegoRespuestasEntity.getIdEstudianteJuego(),estudianteJuegoRespuestasEntity.getIdJuegoPregunta(),
                    estudianteJuegoRespuestasEntity.getIdpreguntas(), estudianteJuegoRespuestasEntity.getIdReto(),
                    estudianteJuegoRespuestasEntity.getRespuesta(), "t".equals(estudianteJuegoRespuestasEntity.getEstado()));
        } else {
            return null;
        }

    }

    @Override
    public EstudianteJuegoRespuesta getIdPreguntas(int idPreguntas) {

        EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = estudianteJuegoRespuestasCrud.findByIdpreguntas(idPreguntas);

        if (estudianteJuegoRespuestasEntity != null) {
            return  new EstudianteJuegoRespuesta(estudianteJuegoRespuestasEntity.getIdEstudianteJuegoRespuestas()
                    ,estudianteJuegoRespuestasEntity.getIdEstudianteJuego(),estudianteJuegoRespuestasEntity.getIdJuegoPregunta(),
                    estudianteJuegoRespuestasEntity.getIdpreguntas(), estudianteJuegoRespuestasEntity.getIdReto(),
                    estudianteJuegoRespuestasEntity.getRespuesta(), "t".equals(estudianteJuegoRespuestasEntity.getEstado()));
        } else {
            return null;
        }
    }


    @Override
    public boolean save( List<EstudianteJuegoRespuesta> listaEstudianteJuegoRespuesta) {

        try {
            //Falta id juego preguntas
            listaEstudianteJuegoRespuesta.forEach(estudianteJuegoRespuesta ->{
                EstudianteJuegoEntity estudianteJuegoEntity = estudianteJuegoCrud
                        .findFirstById_IdEstudianteJuego(estudianteJuegoRespuesta.getIdEstudianteJuego());
                JuegoPreguntasEntity juegoPreguntasEntity = juegoPreguntasCrud
                        .findFirstById_IdJuegoPreguntas(estudianteJuegoRespuesta.getIdjuegoPreguntas());

                EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = new EstudianteJuegoRespuestasEntity();
                estudianteJuegoRespuestasEntity.setIdEstudianteJuegoRespuestas(estudianteJuegoRespuesta.getIdEstudianteJuegoRespuestas());
                estudianteJuegoRespuestasEntity.setIdpreguntas(estudianteJuegoRespuesta.getIdPreguntas());
                estudianteJuegoRespuestasEntity.setIdReto(estudianteJuegoRespuesta.getIdReto());
                estudianteJuegoRespuestasEntity.setIdEstudianteJuego(estudianteJuegoRespuesta.getIdEstudianteJuego());
                estudianteJuegoRespuestasEntity.setIdJuegoPregunta(estudianteJuegoRespuesta.getIdjuegoPreguntas());
                estudianteJuegoRespuestasEntity.setEstado(estudianteJuegoRespuesta.isEstado() ? String.valueOf('t') : String.valueOf('f'));
                estudianteJuegoRespuestasEntity.setEstudianteJuego(estudianteJuegoEntity);
                estudianteJuegoRespuestasEntity.setJuegoPregunta(juegoPreguntasEntity);
                estudianteJuegoRespuestasCrud.save(estudianteJuegoRespuestasEntity);
            });
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    public boolean actualizar(int id, EstudianteJuegoRespuesta estudianteJuegoRespuesta) {

        try {

            EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = new EstudianteJuegoRespuestasEntity();
            estudianteJuegoRespuestasEntity.setIdEstudianteJuegoRespuestas(estudianteJuegoRespuesta.getIdEstudianteJuegoRespuestas());
            // estudianteJuegoRespuestasEntity.getJuegoPregunta().setIdJuegoPreguntas(estudianteJuegoRespuesta.getIdjuegoPreguntas());
            estudianteJuegoRespuestasEntity.setIdpreguntas(estudianteJuegoRespuesta.getIdPreguntas());
            estudianteJuegoRespuestasEntity.setIdReto(estudianteJuegoRespuesta.getIdReto());
            estudianteJuegoRespuestasEntity.setEstado(estudianteJuegoRespuesta.isEstado() ? String.valueOf('t') : String.valueOf('f'));
            estudianteJuegoRespuestasCrud.save(estudianteJuegoRespuestasEntity);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idEstudianteJuegosRespuestas) {

        if (estudianteJuegoRespuestasCrud.findFirstByIdEstudianteJuegoRespuestas(idEstudianteJuegosRespuestas) != null) {

            EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = estudianteJuegoRespuestasCrud.findFirstByIdEstudianteJuegoRespuestas(idEstudianteJuegosRespuestas);
            estudianteJuegoRespuestasEntity.setEstado("f");
            estudianteJuegoRespuestasCrud.save(estudianteJuegoRespuestasEntity);
            return true;
        } else {
            return false;
        }
    }
}