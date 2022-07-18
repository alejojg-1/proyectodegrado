package co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.RetoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RetoCrud extends CrudRepository<RetoEntity, Integer> {
    RetoEntity findFirstByTipo(String tipo);
    RetoEntity findFirstByTitulo(String titulo);
    RetoEntity findFirstByIdReto(int idReto);
    List<RetoEntity> findByIdCursos(int idCursos);
    List<RetoEntity> findByIdCursosAndTipo(int idCursos, String tipo);

}
