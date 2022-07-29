package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.model.CursoEstudiante;
import co.proyectoGrado.repository.CursosEstudiantesRepository;
import co.proyectoGrado.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.repository.persistence.crud.CursoEstudianteCrud;
import co.proyectoGrado.repository.persistence.crud.EstudianteCrud;
import co.proyectoGrado.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.repository.persistence.entity.CursoEstudiantePK;
import co.proyectoGrado.repository.persistence.entity.CursosEstudiantesEntity;
import co.proyectoGrado.repository.persistence.entity.EstudianteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoEstudiantesRepositoryImpl implements CursosEstudiantesRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CursoEstudiantesRepositoryImpl.class);
    private final CursoEstudianteCrud cursoEstudianteCrud;
    private final EstudianteCrud estudianteCrud;
    private final CursoCrud cursoCrud;
    private static final String ERROR_ACTUALIZAR_EL_CURSO_ESTUDIANTE = "Error actualizando estudiante";
    private static final String ERROR_CREANDO_EL_CURSO_ESTUDIANTE = "Error creando estudiante";


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
            cursosEstudiantesEntity.setId(new CursoEstudiantePK());
            cursosEstudiantesEntity.getId().setIdCursoEstudiante(cursoEstudiante.getIdCursoEstudiante());
            cursosEstudiantesEntity.getId().setIdEstudiantes(cursoEstudiante.getIdEstudiantes());
            cursosEstudiantesEntity.getId().setIdCursos(cursoEstudiante.getIdCursos());
            cursosEstudiantesEntity.setEstudiante(estudianteEntity);
            cursosEstudiantesEntity.setCurso(cursoEntity);
            cursoEstudianteCrud.save(cursosEstudiantesEntity);
            return true;
        } catch (Exception e) {
            LOGGER.error(ERROR_CREANDO_EL_CURSO_ESTUDIANTE,e);
            throw new ExcepcionDeProceso(ERROR_CREANDO_EL_CURSO_ESTUDIANTE);
        }
    }

    @Override
    public Boolean actualizar(int id,CursoEstudiante cursoEstudiante) {
        try {
            EstudianteEntity estudianteEntity = estudianteCrud.findFirstByIdEstudiantes(cursoEstudiante.getIdEstudiantes());
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(cursoEstudiante.getIdCursos());

            CursosEstudiantesEntity cursosEstudiantesEntity = new CursosEstudiantesEntity();
            cursosEstudiantesEntity.setId(new CursoEstudiantePK());
            cursosEstudiantesEntity.getId().setIdCursoEstudiante(cursoEstudiante.getIdCursoEstudiante());
            cursosEstudiantesEntity.getId().setIdEstudiantes(cursoEstudiante.getIdEstudiantes());
            cursosEstudiantesEntity.getId().setIdCursos(cursoEstudiante.getIdCursos());
            cursosEstudiantesEntity.setEstudiante(estudianteEntity);
            cursosEstudiantesEntity.setCurso(cursoEntity);
            cursoEstudianteCrud.save(cursosEstudiantesEntity);
            return true;
        } catch (Exception e) {
            LOGGER.error(ERROR_ACTUALIZAR_EL_CURSO_ESTUDIANTE,e);
            throw new ExcepcionDeProceso(ERROR_ACTUALIZAR_EL_CURSO_ESTUDIANTE);
        }
    }

    @Override
    public Boolean delete(int idCursoEstudiantes) {
        if(cursoEstudianteCrud.findById_IdCursoEstudiante(idCursoEstudiantes)!=null){
            CursosEstudiantesEntity cursosEstudianteEntity =  cursoEstudianteCrud.findById_IdCursoEstudiante(idCursoEstudiantes);
            cursoEstudianteCrud.delete(cursosEstudianteEntity);
            return true;
        }else{
            return false;
        }
    }
}




