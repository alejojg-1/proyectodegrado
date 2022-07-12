package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.proyectoGrado.domain.repository.EstudianteJuegoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteJuegoRespuestasCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteJuegoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteJuegoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteJuegoRespuestasEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.RetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class EstudianteJuegoRespositoryImpl implements EstudianteJuegoRepository {

    private final EstudianteJuegoCrud estudianteJuegoCrud;
    private final EstudianteCrud estudianteCrud;
    private final RetoCrud retoCrud;
    private final EstudianteJuegoRespuestasCrud estudianteJuegoRespuestasCrud;

    @Autowired
    public EstudianteJuegoRespositoryImpl(EstudianteJuegoCrud estudianteJuegoCrud, EstudianteCrud estudianteCrud, RetoCrud retoCrud, EstudianteJuegoRespuestasCrud estudianteJuegoRespuestasCrud) {
        this.estudianteJuegoCrud = estudianteJuegoCrud;
        this.estudianteCrud = estudianteCrud;
        this.retoCrud = retoCrud;
        this.estudianteJuegoRespuestasCrud = estudianteJuegoRespuestasCrud;
    }

    @Override
    public List<EstudianteJuego> getAll() {
         List<EstudianteJuego> estudianteJuegos = new ArrayList<>();
         estudianteJuegoCrud.findAll().forEach(estudianteJuegoEntity -> {
             EstudianteJuego estudianteJuego = new EstudianteJuego(estudianteJuegoEntity.getIdEstudianteJuego(),
                     estudianteJuegoEntity.getCalificacion(),estudianteJuegoEntity.getReto().getIdReto(),
                     estudianteJuegoEntity.getId().getIdEstudiantes(),estudianteJuegoEntity.getId().getIdEstudianteJuegoRespuesta());

             estudianteJuegos.add(estudianteJuego);
         });
        return estudianteJuegos;
    }

    @Override
    public EstudianteJuego getByIdReto(int idReto) {

        EstudianteJuegoEntity estudianteJuegoEntity = estudianteJuegoCrud.findFirstByReto_IdReto(idReto);
        if(estudianteJuegoEntity!=null){
            return new EstudianteJuego(estudianteJuegoEntity.getIdEstudianteJuego(),
                    estudianteJuegoEntity.getCalificacion(),estudianteJuegoEntity.getReto().getIdReto(),
                    estudianteJuegoEntity.getId().getIdEstudiantes(),estudianteJuegoEntity.getId().getIdEstudianteJuegoRespuesta());
        }else{
            return null;
        }

    }

    @Override
    public EstudianteJuego getByIdEstudiantes(int idEstudianteJuego) {

        EstudianteJuegoEntity estudianteJuegoEntity = estudianteJuegoCrud.findByIdEstudianteJuego(idEstudianteJuego);
        if(estudianteJuegoEntity!=null){
            return new EstudianteJuego(estudianteJuegoEntity.getIdEstudianteJuego(),
                    estudianteJuegoEntity.getCalificacion(),estudianteJuegoEntity.getReto().getIdReto(),
                    estudianteJuegoEntity.getId().getIdEstudiantes(),estudianteJuegoEntity.getId().getIdEstudianteJuegoRespuesta());
        }else{
            return null;
        }
    }

    @Override
    public boolean save(EstudianteJuego estudianteJuego) {
        try{

            EstudianteEntity estudianteEntity= estudianteCrud.findFirstByIdEstudiantes(estudianteJuego.getIdEstudiantes());
            RetoEntity retoEntity= retoCrud.findByIdReto(estudianteJuego.getIdReto());
            EstudianteJuegoRespuestasEntity estudianteJuegoRespuestasEntity= estudianteJuegoRespuestasCrud.findFirstByIdEstudianteJuegoRespuestas(estudianteJuego.getIdEstudianteJuego());

            /*EstudianteJuegoEntity estudianteJuegoEntity = new EstudianteJuegoEntity(estudianteJuego.getIdEstudianteJuego(),
                    estudianteJuego.getCalificacion(),estudianteJuego.getIdReto(),
                    estudianteJuego.getIdEstudianteJuego(),
                    estudianteJuego.getIdEstudiantes(),retoEntity,
                    estudianteJuegoRespuestasEntity,estudianteEntity);*/



        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    @Override
    public Boolean actualizar(int id,EstudianteJuego estudianteJuego) {
        try{
            EstudianteJuegoEntity estudianteJuegoEntity = new EstudianteJuegoEntity();

            estudianteJuegoEntity.setIdEstudianteJuego(estudianteJuego.getIdEstudianteJuego());
            estudianteJuegoEntity.setCalificacion(estudianteJuego.getCalificacion());
            estudianteJuegoEntity.getReto().setIdReto(estudianteJuego.getIdReto());
            estudianteJuegoEntity.getId().setIdEstudiantes(estudianteJuego.getIdEstudiantes());

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    @Override
    public Boolean delete(int idEstudianteJuego) {
        if(estudianteJuegoCrud.findByIdEstudianteJuego(idEstudianteJuego)!=null){
            EstudianteJuegoEntity estudianteJuegoEntity = ( EstudianteJuegoEntity) estudianteJuegoCrud.findByIdEstudianteJuego(idEstudianteJuego);
            estudianteJuegoCrud.save(estudianteJuegoEntity);
            return true;
        }else{
            return false;
        }
    }

}
