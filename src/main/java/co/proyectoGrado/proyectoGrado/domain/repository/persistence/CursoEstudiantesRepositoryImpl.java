package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.proyectoGrado.domain.repository.CursosEstudiantesRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoEstudianteCrud;
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
            CursoEstudiante cursoEstudiante = new CursoEstudiante(cursosEstudiantesEntity.getId().getIdCursoEstudiante(),
                    cursosEstudiantesEntity.getEstudiante().getIdEstudiantes(),cursosEstudiantesEntity.getCurso().getIdCursos());

            cursoEstudiantes.add(cursoEstudiante);
        });

        return cursoEstudiantes;
    }

    @Override
    public CursoEstudiante getIdEstudiante(int idEstudiante) {
        CursosEstudiantesEntity cursosEstudiantesEntity = cursoEstudianteCrud.findFirstByEstudiante_IdEstudiantes(idEstudiante);

        if (cursosEstudiantesEntity != null) {
            return new CursoEstudiante(cursosEstudiantesEntity.getId().getIdCursoEstudiante(),
                    cursosEstudiantesEntity.getEstudiante().getIdEstudiantes(),cursosEstudiantesEntity.getCurso().getIdCursos());
        } else {
            return null;
        }
    }

    @Override
    public List<CursoEstudiante> getByIdEstudiante(int idEstudiante) {
        List<CursoEstudiante> listaCursoEstudiantes = new ArrayList<>();
        cursoEstudianteCrud.findById_IdEstudiantes(idEstudiante).forEach(cursosEstudiantesEntity -> {
            CursoEstudiante cursoEstudiante = new CursoEstudiante(cursosEstudiantesEntity.getId().getIdCursoEstudiante()
                    ,cursosEstudiantesEntity.getId().getIdEstudiantes(),
                    cursosEstudiantesEntity.getId().getIdCursos());
            listaCursoEstudiantes.add(cursoEstudiante);
        });
        return listaCursoEstudiantes;
    }

    @Override
    public List<CursoEstudiante> getIdCursos(int idCursos) {
        List<CursoEstudiante> listaCursoEstudiantes = new ArrayList<>();
        List<CursosEstudiantesEntity> listaCursosEstudiantesEntity = cursoEstudianteCrud.findById_IdCursos(idCursos);
        if (!listaCursosEstudiantesEntity.isEmpty()) {
            listaCursosEstudiantesEntity.forEach(cursosEstudiantesEntity -> {
                listaCursoEstudiantes.add(new CursoEstudiante(cursosEstudiantesEntity.getId().getIdCursoEstudiante(),
                        cursosEstudiantesEntity.getEstudiante().getIdEstudiantes(),cursosEstudiantesEntity.getCurso().getIdCursos()));
            });
            return listaCursoEstudiantes;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean save(CursoEstudiante cursoEstudiante) {
        try {
            EstudianteEntity estudianteEntity = estudianteCrud.findFirstByIdEstudiantes(cursoEstudiante.getIdEstudiantes());
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(cursoEstudiante.getIdCursos());

            CursosEstudiantesEntity cursosEstudiantesEntity = new CursosEstudiantesEntity();
            cursosEstudiantesEntity.getId().setIdCursoEstudiante(cursoEstudiante.getIdCursoEstudiante());
            cursosEstudiantesEntity.setEstudiante(estudianteEntity);
            cursosEstudiantesEntity.setCurso(cursoEntity);
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
        if(cursoEstudianteCrud.findById_IdCursoEstudiante(idCursoEstudiantes)!=null){
            CursosEstudiantesEntity cursosEstudianteEntity =  cursoEstudianteCrud.findFirstByEstudiante_IdEstudiantes(idCursoEstudiantes);
            cursoEstudianteCrud.delete(cursosEstudianteEntity);
            return true;
        }else{
            return false;
        }
    }
}




