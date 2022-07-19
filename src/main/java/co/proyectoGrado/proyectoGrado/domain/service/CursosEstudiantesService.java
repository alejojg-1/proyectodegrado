package co.proyectoGrado.proyectoGrado.domain.service;

import co.proyectoGrado.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.proyectoGrado.domain.repository.CursosEstudiantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursosEstudiantesService {

    private final CursosEstudiantesRepository cursosEstudiantesRepository;

    @Autowired
    public CursosEstudiantesService(CursosEstudiantesRepository cursosEstudiantesRepository) {
        this.cursosEstudiantesRepository = cursosEstudiantesRepository;
    }

    public List<CursoEstudiante> getAll(){
        return cursosEstudiantesRepository.getAll();
    }

   public CursoEstudiante get(int idEstudiantes) {
        return cursosEstudiantesRepository.getIdEstudiante(idEstudiantes);
   }

   public List<CursoEstudiante> getByIdCurso(int idCurso) {
        return cursosEstudiantesRepository.getIdCursos(idCurso);
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
