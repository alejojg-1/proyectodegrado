package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.proyectoGrado.domain.repository.EstudianteJuegoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteJuegoRespuestasCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.*;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteJuegoCrud;
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
            EstudianteJuego estudianteJuego = new EstudianteJuego(estudianteJuegoEntity.getId().getIdEstudianteJuego(),
                    estudianteJuegoEntity.getCalificacion(), estudianteJuegoEntity.getReto().getIdReto(),
                    estudianteJuegoEntity.getId().getIdEstudiantes());

            estudianteJuegos.add(estudianteJuego);
        });
        return estudianteJuegos;
    }

    @Override
    public EstudianteJuego getByIdReto(int idReto) {

        EstudianteJuegoEntity estudianteJuegoEntity = estudianteJuegoCrud.findFirstByReto_IdReto(idReto);
        if (estudianteJuegoEntity != null) {
            return new EstudianteJuego(estudianteJuegoEntity.getId().getIdEstudianteJuego(),
                    estudianteJuegoEntity.getCalificacion(), estudianteJuegoEntity.getReto().getIdReto(),
                    estudianteJuegoEntity.getId().getIdEstudiantes());
        } else {
            return null;
        }

    }

    @Override
    public EstudianteJuego getByIdEstudiantes(int idEstudianteJuego) {

        EstudianteJuegoEntity estudianteJuegoEntity = estudianteJuegoCrud.findFirstById_IdEstudianteJuego(idEstudianteJuego);
        if (estudianteJuegoEntity != null) {
            return new EstudianteJuego(estudianteJuegoEntity.getId().getIdEstudianteJuego(),
                    estudianteJuegoEntity.getCalificacion(), estudianteJuegoEntity.getReto().getIdReto(),
                    estudianteJuegoEntity.getId().getIdEstudiantes());
        } else {
            return null;
        }
    }

    @Override
    public EstudianteJuego save(EstudianteJuego estudianteJuego) {
        try {

            EstudianteEntity estudianteEntity = estudianteCrud.findFirstByIdEstudiantes(estudianteJuego.getIdEstudiantes());
            RetoEntity retoEntity = retoCrud.findFirstByIdReto(estudianteJuego.getIdReto());

            EstudianteJuegoPK estudianteJuegoPK = new EstudianteJuegoPK(estudianteJuego.getIdEstudianteJuego(),
                    estudianteJuego.getIdReto(),estudianteJuego.getIdEstudiantes());
            EstudianteJuegoEntity estudianteJuegoEntity = new EstudianteJuegoEntity(estudianteJuegoPK,
                    estudianteJuego.getCalificacion(),
                    retoEntity,
                    estudianteEntity);

            return  entityToDomain(estudianteJuegoCrud.save(estudianteJuegoEntity));

        } catch (RuntimeException e) {
           throw new RuntimeException(e);
        }
    }

    private EstudianteJuego entityToDomain(EstudianteJuegoEntity estudianteJuegoEntity){
        return new EstudianteJuego(estudianteJuegoEntity.getId().getIdEstudianteJuego(),
                estudianteJuegoEntity.getCalificacion(), estudianteJuegoEntity.getId().getIdReto(),
                estudianteJuegoEntity.getId().getIdEstudiantes());
    }

    @Override
    public Boolean actualizar(int id, EstudianteJuego estudianteJuego) {
        try {
            EstudianteJuegoEntity estudianteJuegoEntity = new EstudianteJuegoEntity();

            estudianteJuegoEntity.getId().setIdEstudianteJuego(estudianteJuego.getIdEstudianteJuego());
            estudianteJuegoEntity.setCalificacion(estudianteJuego.getCalificacion());
            estudianteJuegoEntity.getReto().setIdReto(estudianteJuego.getIdReto());
            estudianteJuegoEntity.getId().setIdEstudiantes(estudianteJuego.getIdEstudiantes());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Boolean delete(int idEstudianteJuego) {
        if (estudianteJuegoCrud.findFirstById_IdEstudianteJuego(idEstudianteJuego) != null) {
            EstudianteJuegoEntity estudianteJuegoEntity = (EstudianteJuegoEntity)
                    estudianteJuegoCrud.findFirstById_IdEstudianteJuego(idEstudianteJuego);
            estudianteJuegoCrud.save(estudianteJuegoEntity);
            return true;
        } else {
            return false;
        }
    }

}
