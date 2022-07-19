package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteJuegoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudianteJuegoCrud extends CrudRepository<EstudianteJuegoEntity, Integer> {
    EstudianteJuegoEntity findFirstByReto_IdReto(int idReto);
    List<EstudianteJuegoEntity> findById_IdReto(int idReto);
    EstudianteJuegoEntity findFirstById_IdEstudianteJuego(int idEstudianteJuego);

}
