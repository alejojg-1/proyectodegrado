package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import co.proyectoGrado.proyectoGrado.domain.repository.EstudianteJuegoRespuestasRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteJuegoRespuestasCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteJuegoRespuestasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EstudianteJuegoRespuestaRepositoryImpl implements EstudianteJuegoRespuestasRepository {


    private final EstudianteJuegoRespuestasCrud estudianteJuegoRespuestasCrud;

    @Autowired
    public EstudianteJuegoRespuestaRepositoryImpl(EstudianteJuegoRespuestasCrud estudianteJuegoRespuestasCrud) {
        this.estudianteJuegoRespuestasCrud = estudianteJuegoRespuestasCrud;
    }

    @Override
    public List<EstudianteJuegoRespuesta> getAll() {
        List<EstudianteJuegoRespuesta> estudianteJuegoRespuestas = new ArrayList<>();

        estudianteJuegoRespuestasCrud.findAll().forEach(estudianteJuegoRespuestasEntity -> {
            EstudianteJuegoRespuesta estudianteJuegoRespuesta = new EstudianteJuegoRespuesta(estudianteJuegoRespuestasEntity.getIdEstudianteJuegoRespuestas(),
                    estudianteJuegoRespuestasEntity.getIdpreguntas(),estudianteJuegoRespuestasEntity.getIdpreguntas(),
                    estudianteJuegoRespuestasEntity.getIdReto(),"S".equals(estudianteJuegoRespuestasEntity.getEstado()));
            estudianteJuegoRespuestas.add(estudianteJuegoRespuesta);
        });
        return estudianteJuegoRespuestas;
    }

    @Override
    public EstudianteJuegoRespuesta getByIdJuegoPregunta(int idJuegoPregunta) {

        EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = estudianteJuegoRespuestasCrud.findByJuegoPregunta_IdJuegoPreguntas(idJuegoPregunta);

        if (estudianteJuegoRespuestasEntity != null) {
            return new  EstudianteJuegoRespuesta(estudianteJuegoRespuestasEntity.getIdEstudianteJuegoRespuestas(),
                    estudianteJuegoRespuestasEntity.getIdpreguntas(),estudianteJuegoRespuestasEntity.getIdpreguntas(),
                    estudianteJuegoRespuestasEntity.getIdReto(),"S".equals(estudianteJuegoRespuestasEntity.getEstado()));
        } else {
            return null;
        }

    }

    @Override
    public EstudianteJuegoRespuesta getIdPreguntas(int idPreguntas) {

        EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = estudianteJuegoRespuestasCrud.findByIdpreguntas(idPreguntas);

        if (estudianteJuegoRespuestasEntity != null) {
            return new  EstudianteJuegoRespuesta(estudianteJuegoRespuestasEntity.getIdEstudianteJuegoRespuestas(),
                    estudianteJuegoRespuestasEntity.getIdpreguntas(),estudianteJuegoRespuestasEntity.getIdpreguntas(),
                    estudianteJuegoRespuestasEntity.getIdReto(),"S".equals(estudianteJuegoRespuestasEntity.getEstado()));
        } else {
            return null;
        }
    }


    @Override
    public boolean save(EstudianteJuegoRespuesta estudianteJuegoRespuesta) {

        try {

            EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = new EstudianteJuegoRespuestasEntity();
            estudianteJuegoRespuestasEntity.setIdEstudianteJuegoRespuestas(estudianteJuegoRespuesta.getIdEstudianteJuegoRespuestas());
            estudianteJuegoRespuestasEntity.getJuegoPregunta().setIdJuegoPreguntas(estudianteJuegoRespuesta.getIdjuegoPreguntas());
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
    public boolean actualizar(int id, EstudianteJuegoRespuesta estudianteJuegoRespuesta) {

        try {

            EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = new EstudianteJuegoRespuestasEntity();
            estudianteJuegoRespuestasEntity.setIdEstudianteJuegoRespuestas(estudianteJuegoRespuesta.getIdEstudianteJuegoRespuestas());
            estudianteJuegoRespuestasEntity.getJuegoPregunta().setIdJuegoPreguntas(estudianteJuegoRespuesta.getIdjuegoPreguntas());
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

        if(estudianteJuegoRespuestasCrud.findFirstByIdEstudianteJuegoRespuestas(idEstudianteJuegosRespuestas)!=null){


            EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity = estudianteJuegoRespuestasCrud.findFirstByIdEstudianteJuegoRespuestas(idEstudianteJuegosRespuestas);
            estudianteJuegoRespuestasEntity.setEstado("f");
            estudianteJuegoRespuestasCrud.save(estudianteJuegoRespuestasEntity);
            return true;
        }else{
            return false;
        }
    }
}
