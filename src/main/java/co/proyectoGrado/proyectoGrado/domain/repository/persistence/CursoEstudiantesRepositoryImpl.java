package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.proyectoGrado.domain.repository.CursosEstudiantesRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoEstudianteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.DocenteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.EstudianteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursosEstudiantesEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.EstudianteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoEstudiantesRepositoryImpl implements CursosEstudiantesRepository {
    private final CursoEstudianteCrud cursoEstudianteCrud;
    private final EstudianteCrud estudianteCrud;
    private final CursoCrud cursoCrud;

    @Autowired
    public CursoEstudiantesRepositoryImpl(CursoEstudianteCrud cursoEstudianteCrud,EstudianteCrud estudianteCrud,CursoCrud cursoCrud) {
        this.cursoEstudianteCrud = cursoEstudianteCrud;
        this.estudianteCrud = estudianteCrud;
        this.cursoCrud = cursoCrud;
    }

    @Override
    public List<CursoEstudiante> getAll() {
        List<CursoEstudiante> cursoEstudiantes = new ArrayList<>();

        cursoEstudianteCrud.findAll().forEach(cursosEstudiantesEntity -> {
            CursoEstudiante cursoEstudiante = new CursoEstudiante(cursosEstudiantesEntity.getIdCursoEstudiante(),cursosEstudiantesEntity.getEstudiante().getIdEstudiantes(),cursosEstudiantesEntity.getCurso().getIdCursos());

            cursoEstudiantes.add(cursoEstudiante);
        });

        return cursoEstudiantes;
    }

    @Override
    public CursoEstudiante getIdEstudiantes(int idEstudiante) {
        CursosEstudiantesEntity cursosEstudiantesEntity = cursoEstudianteCrud.findFirstByEstudiante_IdEstudiantes(idEstudiante);

        if (cursosEstudiantesEntity != null) {
            return new CursoEstudiante(cursosEstudiantesEntity.getIdCursoEstudiante(),cursosEstudiantesEntity.getEstudiante().getIdEstudiantes(),cursosEstudiantesEntity.getCurso().getIdCursos());
        } else {
            return null;
        }
    }
    @Override
    public CursoEstudiante getIdCursos(int idCursos) {
        CursosEstudiantesEntity cursosEstudiantesEntity = cursoEstudianteCrud.findFirstByCurso_IdCursos(idCursos);

        if (cursosEstudiantesEntity != null) {
            return new CursoEstudiante(cursosEstudiantesEntity.getIdCursoEstudiante(),cursosEstudiantesEntity.getEstudiante().getIdEstudiantes(),cursosEstudiantesEntity.getCurso().getIdCursos());
        } else {
            return null;
        }
    }

    @Override
    public boolean save(CursoEstudiante cursoEstudiante) {
        try {
            EstudianteEntity estudianteEntity = estudianteCrud.findFirstByIdEstudiantes(cursoEstudiante.getIdEstudiantes());
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(cursoEstudiante.getIdCursos());
            CursosEstudiantesEntity cursosEstudiantesEntity = new CursosEstudiantesEntity( cursoEstudiante.getIdCursoEstudainte(),estudianteEntity, cursoEntity);
            cursoEstudianteCrud.save(cursosEstudiantesEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override

    public Boolean actualizar(int id,CursoEstudiante cursoEstudiante) {
        try {
            CursosEstudiantesEntity cursosEstudiantesEntity = new CursosEstudiantesEntity();

            cursosEstudiantesEntity.getEstudiante().setIdentificacion(cursoEstudiante.getIdEstudiantes());
            cursosEstudiantesEntity.getCurso().setIdCursos(cursoEstudiante.getIdCursos());

            cursoEstudianteCrud.save(cursosEstudiantesEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean delete(int idCursoEstudiantes) {
        if(cursoEstudianteCrud.findByIdCursoEstudiante(idCursoEstudiantes)!=null){
            CursosEstudiantesEntity cursosEstudianteEntity =  cursoEstudianteCrud.findFirstByEstudiante_IdEstudiantes(idCursoEstudiantes);
            cursoEstudianteCrud.delete(cursosEstudianteEntity);
            return true;
        }else{
            return false;
        }
    }
}




