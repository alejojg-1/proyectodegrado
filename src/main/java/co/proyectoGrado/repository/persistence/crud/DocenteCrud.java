package co.proyectoGrado.repository.persistence.crud;

import co.proyectoGrado.repository.persistence.entity.DocenteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocenteCrud extends CrudRepository<DocenteEntity, Integer> {

    List<DocenteEntity> findByIdDocentes(int idDocente);
    DocenteEntity findFirstByIdDocentes(int idDocente);
    DocenteEntity findFirstByIdentificacion(int identificacion);
    DocenteEntity findFirstByCorreo(String correo);
}
