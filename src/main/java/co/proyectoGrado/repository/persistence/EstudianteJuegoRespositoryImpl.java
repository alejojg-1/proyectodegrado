package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.repository.EstudianteJuegoRepository;
import co.proyectoGrado.repository.persistence.crud.EstudianteCrud;
import co.proyectoGrado.repository.persistence.crud.EstudianteJuegoCrud;
import co.proyectoGrado.repository.persistence.crud.EstudianteJuegoRespuestasCrud;
import co.proyectoGrado.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.repository.persistence.entity.EstudianteEntity;
import co.proyectoGrado.repository.persistence.entity.EstudianteJuegoEntity;
import co.proyectoGrado.repository.persistence.entity.EstudianteJuegoPK;
import co.proyectoGrado.repository.persistence.entity.RetoEntity;
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
    public EstudianteJuegoRespositoryImpl(EstudianteJuegoCrud estudianteJuegoCrud,
                                          EstudianteCrud estudianteCrud, RetoCrud retoCrud,
                                          EstudianteJuegoRespuestasCrud estudianteJuegoRespuestasCrud) {
        this.estudianteJuegoCrud = estudianteJuegoCrud;
        this.estudianteCrud = estudianteCrud;
        this.retoCrud = retoCrud;
        this.estudianteJuegoRespuestasCrud = estudianteJuegoRespuestasCrud;
    }

    @Override
    public List<EstudianteJuego> getAll() {
        List<EstudianteJuego> estudianteJuegos = new ArrayList<>();
        estudianteJuegoCrud.findAll().forEach(estudianteJuegoEntity -> {
            estudianteJuegos.add(entityToDomain(estudianteJuegoEntity));
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
    public List<EstudianteJuego> obtenerListaPorIdReto(int idReto) {
        List<EstudianteJuego> estudianteJuegos = new ArrayList<>();
        estudianteJuegoCrud.findById_IdReto(idReto).forEach(estudianteJuegoEntity -> {
            estudianteJuegos.add(entityToDomain(estudianteJuegoEntity));
        });
        return estudianteJuegos;
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

    @Override
    public Boolean actualizar(int id, EstudianteJuego estudianteJuego) {
        try {
            EstudianteEntity estudianteEntity = estudianteCrud.findFirstByIdEstudiantes(estudianteJuego.getIdEstudiantes());
            RetoEntity retoEntity = retoCrud.findFirstByIdReto(estudianteJuego.getIdReto());

            EstudianteJuegoPK estudianteJuegoPK = new EstudianteJuegoPK(estudianteJuego.getIdEstudianteJuego(),
                    estudianteJuego.getIdReto(),estudianteJuego.getIdEstudiantes());
            EstudianteJuegoEntity estudianteJuegoEntity = new EstudianteJuegoEntity(estudianteJuegoPK,
                    estudianteJuego.getCalificacion(),
                    retoEntity,
                    estudianteEntity);
             estudianteJuegoCrud.save(estudianteJuegoEntity);

            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delete(int idEstudianteJuego) {
        if (estudianteJuegoCrud.findFirstById_IdEstudianteJuego(idEstudianteJuego) != null) {
            EstudianteJuegoEntity estudianteJuegoEntity = estudianteJuegoCrud.findFirstById_IdEstudianteJuego(idEstudianteJuego);
            estudianteJuegoCrud.delete(estudianteJuegoEntity);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private EstudianteJuego entityToDomain(EstudianteJuegoEntity estudianteJuegoEntity){
        return new EstudianteJuego(estudianteJuegoEntity.getId().getIdEstudianteJuego(),
                estudianteJuegoEntity.getCalificacion(), estudianteJuegoEntity.getId().getIdReto(),
                estudianteJuegoEntity.getId().getIdEstudiantes());
    }

}
