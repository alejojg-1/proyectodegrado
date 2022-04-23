package co.proyectoGrado.proyectoGrado.domain.service;

import co.proyectoGrado.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.proyectoGrado.domain.repository.CursosEstudiantesRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoEstudianteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursosEstudiantesEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursosEstudiantesService {

    private final CursosEstudiantesRepository cursosEstudiantesRepository;
    @Autowired
    private CursoEstudianteCrud cursoEstudianteCrud;

    @Autowired
    public CursosEstudiantesService(CursosEstudiantesRepository cursosEstudiantesRepository) {
        this.cursosEstudiantesRepository = cursosEstudiantesRepository;
    }
   private final ModelMapper mapper = new ModelMapper();

   public CursoEstudiante get(int idEstudiantes) {
        return cursosEstudiantesRepository.getIdEstudiantes(idEstudiantes);
   }

   public boolean save(CursoEstudiante cursoEstudiante) {
        try {
            cursosEstudiantesRepository.save(cursoEstudiante);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
   }

    public Boolean actualizar(int id, CursoEstudiante cursoestudiante) {
        return  cursosEstudiantesRepository.actualizar(id, cursoestudiante);
    }

    public Boolean eliminar(int id) {
        return cursosEstudiantesRepository.delete(id);
    }
}
